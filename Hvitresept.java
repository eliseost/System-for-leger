class Hvitresept extends Resept{

  Hvitresept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }
  public String farge(){
    return "hvit";
  }

  public double prisAaBetale(){
    double pris = legemiddel.hentPris();
    return pris;
  }

  public String toString(){
    return legemiddel.hentNavn() + " utskrevet av " + utskrivendeLege.hentNavn() + " til pasient " +
    pasient.hentNavn() + " med " + reit + " reit.";
  }
}
