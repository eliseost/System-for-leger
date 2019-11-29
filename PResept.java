class PResept extends Hvitresept{


  PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
    super(legemiddel, utskrivendeLege, pasient, 3);
  }

  public double prisAaBetale(){
    double pris = legemiddel.hentPris();
    double maaBetale = pris - 108;
    if(maaBetale < 0){
      maaBetale = 0;
    }
    return maaBetale;
  }

  public String toString(){
    return legemiddel.hentNavn() + " utskrevet av " + utskrivendeLege.hentNavn() + " til pasient " +
    pasient.hentNavn() + " med " + reit + " reit.";
  }
}
