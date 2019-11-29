class Legemiddel{
  protected String navn;
  protected double pris;
  protected double virkestoff;
  static int id = 0;  /*skal være private*/
  protected int minID;

  Legemiddel(String navn, double pris, double virkestoff){
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    minID = id;
    id++;
  }

  public int hentID(){
    return minID;
  }

  public String hentNavn(){
    return navn;
  }

  public double hentPris(){
    return pris;
  }

  public double hentVirkestoff(){
    return virkestoff;
  }

  public void settNyPris(int nyPris){
    pris = nyPris;
  }
}
