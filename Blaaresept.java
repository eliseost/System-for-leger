class Blaaresept extends Resept{

  Blaaresept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  public String farge(){
    return "blaa";
  }

  public double prisAaBetale(){
    double pris = legemiddel.hentPris();
    double maaBetale = 0.75*pris;
    return maaBetale;
  }

  public String toString(){
    return legemiddel.hentNavn() + " utskrevet av " + utskrivendeLege.hentNavn() + " til pasient " +
    pasient.hentNavn() + " med " + reit + " reit.";
  }
}
