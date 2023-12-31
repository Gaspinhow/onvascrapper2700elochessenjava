package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ItemsScrapper {
    ArrayList<Item> parseSource(String pageSource) {
        ArrayList<Item> itemList = new ArrayList<>();
        int index = pageSource.indexOf("<tr class");
        while (true) {
            // Nom Joueur
            int indexNomJoueur = pageSource.indexOf("<a href=\"/players/", index);
            if (indexNomJoueur == -1) {
                break; 
            }
            int indexNomJoueurFin = pageSource.indexOf("</a></td>", indexNomJoueur);
            if (indexNomJoueurFin == -1) {
                break;  
            }
            String nomJoueurHtml = pageSource.substring(indexNomJoueur, indexNomJoueurFin);
            String nomJoueur = nomJoueurHtml.replaceAll("<.*?>", "").trim();

            //Pays Joueur 
               
               int indexPaysJoueur = pageSource.indexOf("<td class=\"country f24\"><span class=\"hidden searched\">", index);
               int indexPaysJoueurFin = pageSource.indexOf("</span> ", indexPaysJoueur);
               if (indexPaysJoueur == -1 || indexPaysJoueurFin == -1) {
                   break;  
               }
               String paysJoueurHtml = pageSource.substring(indexPaysJoueur, indexPaysJoueurFin);
               String paysJoueur = paysJoueurHtml.replaceAll("<.*?>", "").trim();
            
            //Elo Classique

            int indexEloClassic = pageSource.indexOf("the rank at that time\">", index);
            int indexEloClassicFin = pageSource.indexOf("</strong></td>", indexEloClassic);
            if (indexEloClassic == -1 || indexEloClassicFin == -1) {
                break;  
            }
            String eloClassicString = pageSource.substring(indexEloClassic, indexEloClassicFin)
                    .replaceAll("[^0-9.]", ""); 

            double eloClassic = 0.0;
            if (pageSource.contains("live_standard_rating")) {
                eloClassic = Double.parseDouble(eloClassicString);
            }

            //PeakElo
            double peakElo = 0.0;
            int indexPeakElo = pageSource.indexOf("data-placement=\"top\" title=\"Best Rating", index);
            int indexPeakEloFin = pageSource.indexOf("- ", indexPeakElo);
            
            if (indexPeakElo >= 0 && indexPeakEloFin > indexPeakElo) {
                String peakEloSubstring = pageSource.substring(indexPeakElo, indexPeakEloFin);
                String numericPart = peakEloSubstring.replaceAll("[^\\d.]", "");
                if (!numericPart.isEmpty()) {
                    peakElo = Double.parseDouble(numericPart);
                }
            }
           //PlusMOINSSHALLAH
           double plusMoins = 0.0;  
           int indexPlusMoins = pageSource.indexOf("class=\"standard_change change\"><span data-sort-value=", index);
           if (indexPlusMoins >= 0) {
           Pattern pattern = Pattern.compile("[-+]?(\\d*\\.?\\d+|\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
           Matcher matcher = pattern.matcher(pageSource.substring(indexPlusMoins));
           if (matcher.find()) {
        plusMoins = Double.parseDouble(matcher.group());
    }
}
        plusMoins -= 1000.0;

             // Elo Rapid
             double eloRapid = 0.0; 
             int indexEloRapid = pageSource.indexOf("live_rapid_rating canhide", index);
             if (indexEloRapid == -1) {
            } else {
            int indexEloRapidValue = pageSource.indexOf("data-sort-value=\"", indexEloRapid) + "data-sort-value=\"".length();
            int indexEloRapidFin = pageSource.indexOf("\"", indexEloRapidValue);
            if (indexEloRapidValue == -1 || indexEloRapidFin == -1) {
            } else {
            String eloRapidString = pageSource.substring(indexEloRapidValue, indexEloRapidFin)
                .replaceAll("[^0-9.]", ""); 
             eloRapid = Double.parseDouble(eloRapidString);
            if (eloRapid > 10000) {
            eloRapid /= 100;
           }
           if(eloRapid>200 && eloRapid<300){
            eloRapid *= 10;
           }
        }
     }
           //Classement
            int classement = 0; 
            int indexClassement = pageSource.indexOf("Please log in to check the best ranks\">", index);
            int indexClassementFin = pageSource.indexOf("</span></td>", indexClassement);
            if (indexClassement != -1 && indexClassementFin != -1) {
            String classementString = pageSource.substring(indexClassement, indexClassementFin).replaceAll("\\D", "");
            classement = Integer.parseInt(classementString);
            }
           //Age
           int age = 0;  
           int indexAge = pageSource.indexOf("<td class=\"age\">", index);
           int indexAgeTitle = pageSource.indexOf("title=\"", indexAge);
           int indexAgeTitleEnd = pageSource.indexOf("\">", indexAgeTitle);
           if (indexAge != -1 && indexAgeTitle != -1 && indexAgeTitleEnd != -1) {
           String ageTitle = pageSource.substring(indexAgeTitle + "title=\"".length(), indexAgeTitleEnd);
           int birthYear = Integer.parseInt(ageTitle.substring(ageTitle.lastIndexOf(' ') + 1));
           age = 2023 - birthYear;
           }
           
           //EloBlitz
            double eloBlitz = 0.0; 
            int indexEloBlitz = pageSource.indexOf("live_blitz_rating canhide", index);
            if (indexEloBlitz != -1) {
            int indexEloBlitzValue = pageSource.indexOf("data-sort-value=\"", indexEloBlitz) + "data-sort-value=\"".length();
            int indexEloBlitzFin = pageSource.indexOf("\"", indexEloBlitzValue);
            if (indexEloBlitzValue != -1 && indexEloBlitzFin != -1) {
            String eloBlitzString = pageSource.substring(indexEloBlitzValue, indexEloBlitzFin).replaceAll("[^0-9.]", "");
            eloBlitz = Double.parseDouble(eloBlitzString);
            if (eloBlitz > 10000) {
              eloBlitz /= 100;
          }
            if (eloBlitz > 200 && eloBlitz < 300) {
              eloBlitz *= 10;
          }
      }
      
  }
            

            Item item = new Item();
            item.setEloClassic(eloClassic);
            item.setEloRapid(eloRapid);
            item.setEloBlitz(eloBlitz);
            item.setPlusMoins(plusMoins);
            item.setClassement(classement);
            item.setAge(age);
            item.setNomJoueur(nomJoueur);
            item.setPaysJoueur(paysJoueur);
            item.setPeakElo(peakElo);
            itemList.add(item);

            //Calculer la perf
            double eloBase = item.getEloClassic();
            double plusMoins1 = item.getPlusMoins();
            double coefficientElo = 0.2 + (eloBase / 1200.0); 
            double plusMoinsAjuste = plusMoins1 * coefficientElo;
            double eloAjuste = eloBase + plusMoinsAjuste;
            double variationElo = eloAjuste ;

            item.setPerformance(variationElo);
            

            //Aller a l index suicant
            index = pageSource.indexOf("<tr class", indexNomJoueurFin);
            if (index ==-1) {
                break;  
            }
        }
        return itemList;
    }
    
}
    