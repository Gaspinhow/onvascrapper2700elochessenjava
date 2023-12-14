package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.String;
import java.util.ArrayList;


public final class ItemsScrapper {
  ArrayList<Item> parseSource(String pageSource) {
    ArrayList<Item> itemList = new ArrayList<>();
    int index = pageSource.indexOf("<tr class=\"leaders\">");
    while (true) {
      Item item = new Item();
      // add code here
      int indexClassement = pageSource.indexOf("Please log in to check the best ranks\">", index);
      int indexClassementFin = pageSource.indexOf("</span></td>", indexClassement);
      String classementString = pageSource.substring(indexClassement, indexClassementFin).replaceAll("\\D", ""); // Supprimer les caractères non numériques
      int Classement = Integer.parseInt(classementString);

      int indexEloClassic = pageSource.indexOf("the rank at that time\">", index);
      int indexEloClassicFin = pageSource.indexOf("</strong></td>", indexEloClassic);
      String eloClassicString = pageSource.substring(indexEloClassic, indexEloClassicFin)
        .replaceAll("[^0-9.]", ""); // Supprimer les caractères non numériques
        double eloClassic = 0.0;

        // Vérifier la classe de la colonne Elo Classic
        if (pageSource.contains("live_standard_rating")) {
            eloClassic = Double.parseDouble(eloClassicString);
        }

      int indexPaysJoueur = pageSource.indexOf("<td class=\"country f24\"><span class=\"hidden searched\">", index);
      int indexPaysJoueurFin = pageSource.indexOf("</span> ", indexPaysJoueur);
      String paysJoueur = pageSource.substring(indexPaysJoueur, indexPaysJoueurFin).replaceAll("<.*?>", "").trim();
// Scrapping du nom du joueur
     
int indexNomJoueur = pageSource.indexOf("<a href=\"/players/", index);
int indexNomJoueurFin = pageSource.indexOf("</a></td>", indexNomJoueur);
String nomJoueurHtml = pageSource.substring(indexNomJoueur, indexNomJoueurFin);
String nomJoueur = nomJoueurHtml.replaceAll("<.*?>", "").trim();

int indexEloRapid = pageSource.indexOf("live_rapid_rating canhide", index);
int indexEloRapidValue = pageSource.indexOf("data-sort-value=\"", indexEloRapid) + "data-sort-value=\"".length();
int indexEloRapidFin = pageSource.indexOf("_1\"", indexEloRapidValue);
String eloRapidString = pageSource.substring(indexEloRapidValue, indexEloRapidFin)
    .replaceAll("[^0-9.]", ""); // Supprimer les caractères non numériques

double eloRapid = Double.parseDouble(eloRapidString);

int indexEloBlitz = pageSource.indexOf("live_blitz_rating canhide", index);
int indexEloBlitzValue = pageSource.indexOf("data-sort-value=\"", indexEloBlitz) + "data-sort-value=\"".length();
int indexEloBlitzFin = pageSource.indexOf("_1\"", indexEloBlitzValue);
String eloBlitzString = pageSource.substring(indexEloBlitzValue, indexEloBlitzFin)
    .replaceAll("[^0-9.]", ""); // Supprimer les caractères non numériques

double eloBlitz = Double.parseDouble(eloBlitzString);


int indexPlusMoins = pageSource.indexOf("<td class=\"position_change\"><span data-sort-value=\"", index);
int indexPlusMoinsFin = pageSource.indexOf("\" class=\"hidden\"> </span></td>", indexPlusMoins);
String PlusMoins = pageSource.substring(indexPlusMoins, indexPlusMoinsFin).replaceAll("<.*?>", "").trim();
String plusMoinsValue = PlusMoins.replaceAll("[^0-9.-]", "");

// Supprime le préfixe "--" s'il est présent
if (plusMoinsValue.startsWith("--")) {
    plusMoinsValue = plusMoinsValue.substring(2);
}

double plusMoins = Double.parseDouble(plusMoinsValue) / 1000.0;

// ...
// ...
int indexAge = pageSource.indexOf("<td class=\"age\">", index);
int indexAgeTitle = pageSource.indexOf("title=\"", indexAge);
int indexAgeTitleEnd = pageSource.indexOf("\">", indexAgeTitle);

String ageTitle = pageSource.substring(indexAgeTitle + "title=\"".length(), indexAgeTitleEnd);

// Extraction de l'année de naissance
int birthYear = Integer.parseInt(ageTitle.substring(ageTitle.lastIndexOf(' ') + 1));

// Calcul de l'âge
int age = 2023 - birthYear;
// ...






      

      
      
      item.setEloClassic(eloClassic);
      item.setEloRapid(eloRapid);
      item.setEloBlitz(eloBlitz);
      item.setPlusMoins(plusMoins);
      item.setClassement(Classement);
      item.setAge(age);
      item.setPaysJoueur(paysJoueur);
      item.setNomJoueur(nomJoueur);
      itemList.add(item);
      
      
      if(true) {
        // on a fini d'extraire les item
        break;
      }
    }
    return itemList;
  }
}
