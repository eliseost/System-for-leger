class Militaerresept extends Hvitresept{

  Militaerresept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  public double prisAaBetale(){
    return 0;
  }

  public String toString(){
    return legemiddel.hentNavn() + " utskrevet av " + utskrivendeLege.hentNavn() + " til pasient " +
    pasient.hentNavn() + " med " + reit + " reit.";
  }
}
