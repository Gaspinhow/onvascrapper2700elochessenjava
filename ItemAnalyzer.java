package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
public final class ItemAnalyzer {
  private ArrayList<Item> items = new ArrayList<>();

  public ItemAnalyzer(ArrayList<Item> items) {
    this.items=items;
  }

  public Double getMoyEloClassicTop100() {
    double sommeEloClassic = 0.0;
  for(Item item : items){
    sommeEloClassic+= item.getEloClassic();
  }
    return sommeEloClassic / items.size();
  }

  public String getMeilleurPlusMoins() {
    Item meilleurJoueur = null;

    for(Item joueur : items ){
      if(meilleurJoueur == null || joueur.getPlusMoins() > meilleurJoueur.getPlusMoins()){meilleurJoueur = joueur;
      }
    }
    return (meilleurJoueur != null) ? meilleurJoueur.getNomJoueur() + " avec un +/- de " + meilleurJoueur.getPlusMoins() : "";
  }

  public Integer getAgeMoyenTop100() {
    int sommeAge = 0;
    for(Item joueur:items){
      sommeAge += joueur.getAge();
    }
    return  sommeAge /  items.size();

  }

  public String getPaysPlusRepresent() {
    HashMap<String, Integer> paysCounts = new HashMap<>();
    for (Item joueur : items ){
      String pays = joueur.getPaysJoueur();
      paysCounts.put(pays , paysCounts.getOrDefault(pays, 0) +1);
    }
    int maxCount = 0;
    String paysPlusRepresent = "";

    for(String pays : paysCounts.keySet()){
      int count = paysCounts.get(pays);
      if (count > maxCount){
        maxCount = count;
        paysPlusRepresent = pays;
      }
    }

    return paysPlusRepresent + " avec " + maxCount + " joueurs";
  }

  public String getPaysMeilleurMoyEloClassic() {
    HashMap<String , Double>  moyenneParPays= new HashMap<>();
    HashMap<String , Integer> countParPays = new HashMap<>();
    for (Item joueur :items);{
    String pays = joueur.getPaysJoueur();
    moyenneParPays.put(pays, countParPays.getOrDefault(pays, 0.0)+joueur.getEloClassic());
    countParPays.put(pays , countParPays.getOrDefault(pays, 0) +1);
    }
    double meilleureMoyenne = 0.0;
    String paysMeilleureMoyenne = "";

    for (String pays : moyenneParPays.keySet()) {
      double moyenne = moyenneParPays.get(pays) / countParPays.get(pays);
      if (moyenne > meilleureMoyenne) {
          meilleureMoyenne = moyenne;
          paysMeilleureMoyenne = pays;
      }
  }
  return paysMeilleureMoyenne + " avec une moyenne de " + meilleureMoyenne;

  }

  public Double getMeilleurRapportAgeEloClassic() {
    double meilleurRapport = 0.0;
    for(Item joueur:items){
      double rapport = joueur.getEloClassic()/joueur.getAge();
      if (rapport > meilleurRapport){
        meilleurRapport = rapport;
      }
    }

    return meilleurRapport;
  }

  public double  getPirePlusMoins() {
    Item pireJoueur = null;
    for(Item joueur:items){
      if(pireJoueur ==null|| joueur.getPlusMoins() <pireJoueur.getPlusMoins()){pireJoueur = joueur;
    }
    return (pireJoueur != null) ? pireJoueur.getNomJoueur() + " avec un +/- de " + pireJoueur.getPlusMoins() : "";}
  }

  public String getPaysMoinsRepresent() {
    HashMap<String, Integer> paysCounts = new HashMap<>();
    for (Item joueur : items ){
      String pays = joueur.getPaysJoueur();
      paysCounts.put(pays , paysCounts.getOrDefault(pays, 0) +1);
    }
    int minCount = 0;
    String paysMoinsRepresent = "";

    for(String pays : paysCounts.keySet()){
      int count = paysCounts.get(pays);
      if (count < minCount){
        minCount = count;
        paysMoinsRepresent = pays;
      }
    }

    return paysMoinsRepresent + " avec " + minCount + " joueurs";
  }

  public Double getMedianeEloClassicTop100() {
    int taille = items.size();
    int index1 = taille/2 -1;
    int index2 = taille/2;
    return (items.get(index1).getEloClassic()+items.get(index2).getEloClassic())/2.0;
  }

  public Double getEcartTypeEloClassicTop100() {
    int taille = items.size();
    double moyenne = getMoyEloClassicTop100();
    double sommeCarresEcarts = 0.0;
    for (Item joueur: items );{
      double ecart = joueur.getEloClassi() - moyenne;
      sommeCarresEcarts += ecart * ecart; 
    }
    double variance = sommeCarresEcarts / taille ;
    double ecartType = Math.sqrt(variance);
    return ecartType;
  }

  public Double getMeilleurEloTotal() {
    Double meilleurEloTotal = 0.0;
    for(Item joueur:items){
      Double eloTotal = joueur.getEloClassic()+joueur.getEloBlitz()+joueur.getEloRapid();
      if (eloTotal > meilleurEloTotal){
        meilleurEloTotal = eloTotal;
      }
    }
    return meilleurEloTotal;
  }
}
