package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.String;
import java.util.ArrayList;

public final class ItemsScrapper {
  ArrayList<Item> parseSource(String pageSource) {
    ArrayList<Item> itemList = new ArrayList<>();
    int startIndex = 0;
    while ((startIndex = pageSource.indexOf("<tr class=\"all_lines\">", startIndex)) != -1) { 
      Item item = new Item();
      // add code here
      // Classement
      startIndex = pageSource.indexOf("<span data-sort-value=\"", startIndex) + "<span data-sort-value=\"".length();
      int endIndex = pageSource.indexOf("\"", startIndex);
      item.setClassement(Integer.parseInt(pageSource.substring(startIndex, endIndex).trim()));
      //PlusMoins
      startIndex = pageSource.indexOf("<span data-sort-value=\"", startIndex) + "<span data-sort-value=\"".length();
      endIndex = pageSource.indexOf("\"", startIndex);
      item.setPlusMoins(Double.parseDouble(pageSource.substring(startIndex, endIndex).trim()));
      //Nom du joueur
      startIndex = pageSource.indexOf("<td class=\"name\"><span class=\"hidden searched\"></span> <a href=\"", startIndex) + "<td class=\"name\"><span class=\"hidden searched\"></span> <a href=\"".length();
      endIndex = pageSource.indexOf("</a>", startIndex);
      String playerNameData =  pageSource.substring(startIndex, endIndex);
      int playerLinkStartIndex = playerNameData.indexOf("\">") + "\">".length();
      int playerLinkEndIndex = playerNameData.indexOf("</a>");
      item.setNomJoueur(playerNameData.substring(playerLinkStartIndex, playerLinkEndIndex).trim());
      //Pays du Joueur
      startIndex = pageSource.indexOf("<td class=\"country f24\"><span class=\"hidden searched\">", startIndex) + "<td class=\"country f24\"><span class=\"hidden searched\">".length();
      endIndex = pageSource.indexOf("</span>", startIndex);
      item.setPaysJoueur(pageSource.substring(startIndex, endIndex).trim());
      //EloClassic
      startIndex = pageSource.indexOf("<td class=\"live_standard_rating\"><strong data-sort-value=\"", startIndex) + "<td class=\"live_standard_rating\"><strong data-sort-value=\"".length();
      endIndex = pageSource.indexOf("</strong></td>", startIndex);
      item.setEloClassic(Double.parseDouble(pageSource.substring(startIndex, endIndex).trim()));
      //EloRapid
       startIndex = pageSource.indexOf("<td class=\"live_rapid_rating canhide\"><strong data-sort-value=\"", startIndex) + "<td class=\"live_rapid_rating canhide\"><strong data-sort-value=\"".length();
      endIndex = pageSource.indexOf("</strong></td>", startIndex);
      item.setEloRapid(Double.parseDouble(pageSource.substring(startIndex, endIndex).trim()));
      //EloBlitz
       startIndex = pageSource.indexOf("<td class=\"live_blitz_rating canhide\"><strong data-sort-value=\"", startIndex) + "<td class=\"live_blitz_rating canhide\"><strong data-sort-value=\"".length();
      endIndex = pageSource.indexOf("</strong></td>", startIndex);
      item.setEloBlitz(Double.parseDouble(pageSource.substring(startIndex, endIndex).trim()));
      //Age 
      startIndex = pageSource.indexOf("<td class=\"age\"><span data-sort-value=\"", startIndex) + "<td class=\"age\"><span data-sort-value=\"".length();
      endIndex = pageSource.indexOf("</strong></td>", startIndex);
      item.setAge(Integer.parseInt(pageSource.substring(startIndex, endIndex).trim()));

      itemList.add(item);
      
      if(true) {
        // on a fini d'extraire les item
        break;
      }
    }
    return itemList;
  }
}
