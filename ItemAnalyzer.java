package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ItemAnalyzer {
  private ArrayList<Item> items = new ArrayList<>();

  public ItemAnalyzer(ArrayList<Item> items) {
    this.items=items;
  }
//MoyenneCLassique
  public double getMoyEloClassicTop100() {
    double sommeEloClassic = 0.0;
    for (Item item : items) {
        sommeEloClassic += item.getEloClassic();
    }
    return sommeEloClassic / items.size();
}

//MoyenneRapide
public double getMoyEloRapidTop100() {
  double sommeEloRapid = 0.0;
  for (Item item : items) {
      sommeEloRapid += item.getEloRapid();
  }
  return sommeEloRapid / items.size();
}

//Moyenne Blitz
public double getMoyEloBlitzTop100() {
  double sommeEloBlitz = 0.0;
  for (Item item : items) {
      sommeEloBlitz += item.getEloBlitz();
  }
  return sommeEloBlitz / items.size();
}

//Moyenne Age
  public double getMoyenneAge() {
    double sommeAge = 0.0;
    for (Item item : items) {
        sommeAge += item.getAge();
    }
    return sommeAge / items.size();
}

//PaysPlusRPZ
public String getPaysPlusRepresent() {
  HashMap<String, Integer> countByPays = new HashMap<>();
  for (Item joueur : items) {
      String pays = joueur.getPaysJoueur();
      countByPays.put(pays, countByPays.getOrDefault(pays, 0) + 1);
  }
  String paysPlusRepresent = "";
  int nombreJoueursMax = 0;

  for (Map.Entry<String, Integer> entry : countByPays.entrySet()) {
      String pays = entry.getKey();
      int nombreJoueurs = entry.getValue();

      if (nombreJoueurs > nombreJoueursMax) {
          paysPlusRepresent = pays;
          nombreJoueursMax = nombreJoueurs;
      }
  }
  return paysPlusRepresent.isEmpty()
          ? "" 
          : "Le pays le plus représenté est " + paysPlusRepresent + " avec " + nombreJoueursMax + " joueurs.";
}


//MeilleureMoyenne
public String getPaysMeilleurMoyEloClassic() {
  if (items == null || items.isEmpty()) {
      return ""; 
  }
  HashMap<String, Double> sumByPays = new HashMap<>();
  HashMap<String, Integer> countByPays = new HashMap<>();
  for (Item joueur : items) {
      String pays = joueur.getPaysJoueur();
      double eloClassic = joueur.getEloClassic();

      sumByPays.put(pays, sumByPays.getOrDefault(pays, 0.0) + eloClassic);
      countByPays.put(pays, countByPays.getOrDefault(pays, 0) + 1);
  }

//PaysMeilleureMoyenne
  HashMap<String, Double> moyenneByPays = new HashMap<>();

  for (Map.Entry<String, Double> entry : sumByPays.entrySet()) {
      String pays = entry.getKey();
      double sum = entry.getValue();
      int nombreJoueurs = countByPays.getOrDefault(pays, 0);
      if (nombreJoueurs > 0) {
          double moyenne = sum / nombreJoueurs;
          moyenneByPays.put(pays, moyenne);
      }
  }
  String paysMeilleurMoyenne = "";
  double meilleureMoyenne = Double.MIN_VALUE;

  for (Map.Entry<String, Double> entry : moyenneByPays.entrySet()) {
      String pays = entry.getKey();
      double moyenne = entry.getValue();

      if (moyenne > meilleureMoyenne) {
          paysMeilleurMoyenne = pays;
          meilleureMoyenne = moyenne;
      }
  }
  return paysMeilleurMoyenne.isEmpty()
          ? "" 
          : "Le pays avec la meilleure moyenne d'Elo classique est " + paysMeilleurMoyenne
                  + " avec une moyenne de " + meilleureMoyenne + ".";
}


//PaysMoinsRPZ
  public String getPaysMoinsRepresent() {
    if (items == null || items.isEmpty()) {
        return ""; 
    }
    HashMap<String, Integer> countByPays = new HashMap<>();
    for (Item joueur : items) {
        String pays = joueur.getPaysJoueur();
        countByPays.put(pays, countByPays.getOrDefault(pays, 0) + 1);
    }
    String paysMoinsRepresent = "";
    int nombreJoueursMin = Integer.MAX_VALUE;

    for (Map.Entry<String, Integer> entry : countByPays.entrySet()) {
        String pays = entry.getKey();
        int nombreJoueurs = entry.getValue();

        if (nombreJoueurs < nombreJoueursMin) {
            paysMoinsRepresent = pays;
            nombreJoueursMin = nombreJoueurs;
        }
    }
    return paysMoinsRepresent.isEmpty()
            ? "" 
            : "Le pays le moins représenté est " + paysMoinsRepresent + " avec " + nombreJoueursMin + " joueur(s).";
}


  public Double getMedianeEloClassicTop100() {
    // code here
    return null;
  }

  public Double getEcartTypeEloClassicTop100() {
    // code here
    return null;
  }

//MeilleurEloTotal
  public String getJoueurMeilleurEloTotal() {
    String meilleurJoueur = "";
    double meilleurEloTotal = 0.0;

    for (Item joueur : items) {
        double eloTotal = joueur.getEloClassic() + joueur.getEloRapid() + joueur.getEloBlitz();

        if (eloTotal > meilleurEloTotal) {
            meilleurEloTotal = eloTotal;
            meilleurJoueur = joueur.getNomJoueur();
        }
    }

    return meilleurJoueur + " : " + meilleurEloTotal;
}

//MeilleurRapportELOAGE
  public String getJoueurMeilleurRapportAgeEloClassic() {
    String meilleurJoueur = "";
    double meilleurRapport = 0.0;

    for (Item joueur : items) {
        double rapport = joueur.getEloClassic() / joueur.getAge();

        if (rapport > meilleurRapport) {
            meilleurRapport = rapport;
            meilleurJoueur = joueur.getNomJoueur();
        }
    }

    return meilleurJoueur + " : " + meilleurRapport;
}
//PireRapportELOAGE
public String getJoueurPireRapportAgeEloClassic(){
  String pireJoueur = "";
  double pireRapport = Double.POSITIVE_INFINITY; // Initialiser à une valeur positive infinie pour s'assurer que le premier rapport est toujours plus petit

  for(Item joueur : items) {
      double rapport = joueur.getEloClassic() / joueur.getAge();

      if(rapport < pireRapport){
          pireRapport = rapport;
          pireJoueur = joueur.getNomJoueur();
      }
  }
  return pireJoueur + " : " + pireRapport; // Afficher le rapport plutôt que le nom du joueur deux fois
}



}

