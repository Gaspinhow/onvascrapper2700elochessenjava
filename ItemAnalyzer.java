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
  public double getMoyEloClassic() {
    double sommeEloClassic = 0.0;
    for (Item item : items) {
        sommeEloClassic += item.getEloClassic();
    }
    return sommeEloClassic / items.size();
}

//MoyenneRapide
public double getMoyEloRapid() {
  double sommeEloRapid = 0.0;
  for (Item item : items) {
      sommeEloRapid += item.getEloRapid();
  }
  return sommeEloRapid / items.size();
}

//Moyenne Blitz
public double getMoyEloBlitz() {
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
  return  paysPlusRepresent + " avec " + nombreJoueursMax + " joueurs.";
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
    int nombreJoueursMin = 25;

    for (Map.Entry<String, Integer> entry : countByPays.entrySet()) {
        String pays = entry.getKey();
        int nombreJoueurs = entry.getValue();

        if (nombreJoueurs < nombreJoueursMin) {
            paysMoinsRepresent = pays;
            nombreJoueursMin = nombreJoueurs;
        }
    }
    return   paysMoinsRepresent + " avec " + nombreJoueursMin + " joueur(s).";
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

//PireEloTotal
  public String getJoueurPireEloTotal() {
    String pireJoueur = "";
    double pireEloTotal = 20000;

    for (Item joueur : items) {
        double eloTotal = joueur.getEloClassic() + joueur.getEloRapid() + joueur.getEloBlitz();

        if (eloTotal < pireEloTotal) {
            pireEloTotal = eloTotal;
            pireJoueur = joueur.getNomJoueur();
        }
    }

    return pireJoueur + " : " + pireEloTotal;
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
  double pireRapport = 1000.0; 

  for(Item joueur : items) {
      double rapport = joueur.getEloClassic() / joueur.getAge();

      if(rapport < pireRapport){
          pireRapport = rapport;
          pireJoueur = joueur.getNomJoueur();
      }
  }
  return pireJoueur + " : " + pireRapport; 
}

//MeilleurPlusMoins
public String getJoueurMeilleurPlusMoins() {
  String meilleurJoueur = "";
  double meilleurPlusMoins = Double.NEGATIVE_INFINITY;

  for (Item joueur : items) {
      double plusMoins = joueur.getPlusMoins();

      if (plusMoins > meilleurPlusMoins) {
          meilleurPlusMoins = plusMoins;
          meilleurJoueur = joueur.getNomJoueur();
      }
  }

  return meilleurJoueur + " : " + meilleurPlusMoins;
}
//PirePlusMoins
public String getJoueurPirePlusMoins() {
  String pireJoueur = "";
  double pirePlusMoins = 25.0;

  for(Item joueur : items) {
      double plusMoins = joueur.getPlusMoins();

      if(plusMoins < pirePlusMoins){
          pirePlusMoins = plusMoins;
          pireJoueur = joueur.getNomJoueur();
      }
  }

  return pireJoueur + " : " + pirePlusMoins;
}

//JoueurLePlusJeune
public String getJoueurLePlusJeune() {
  String joueurLePlusJeune = "";
    int ageMin = 100;

    for (Item joueur : items) {
        int ageJoueur = joueur.getAge();
        if (ageJoueur < ageMin) {
            joueurLePlusJeune = joueur.getNomJoueur();
            ageMin = ageJoueur;
        }
    }

    return joueurLePlusJeune + " " + ageMin + " ans.";
          
  }

  //JoueurLePlusVieux

  public String getJoueurLePlusVieux(){
    String joueurLePlusVieux = "";
    int ageMax = 0;

    for (Item joueur : items){
      int ageJoueur = joueur.getAge();
      if (ageJoueur > ageMax){
        joueurLePlusVieux = joueur.getNomJoueur();
        ageMax = ageJoueur;
      }
    }
    return   joueurLePlusVieux+ " " + ageMax + " ans.";
          
    }
  //JoueurrMeilleurePerformance

  public String getJoueurMeilleurePerf(){
    String joueurMeilleurePerf = "";
    double  perfMax = 0.0;
    
    for (Item joueur : items){
      double perfJoueur = joueur.getPerformance();
      if (perfJoueur > perfMax){
        joueurMeilleurePerf = joueur.getNomJoueur();
        perfMax = perfJoueur;
      }
    }
     return  joueurMeilleurePerf+ " " + perfMax + "elo";
  }
  //JoueurPirePerformance
  public String getJoueurPirePerf(){
    String joueurPirePerf = "";
    double  perfMin = 3000.0;
    
    for (Item joueur : items){
      double perfJoueur = joueur.getPerformance();
      if (perfJoueur < perfMin){
        joueurPirePerf = joueur.getNomJoueur();
        perfMin = perfJoueur;
      }
    }
     return  joueurPirePerf+ " " + perfMin + "elo";
  }
}