class PreparatA extends Legemiddel{
  protected int styrke;

  PreparatA(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public int hentNarkotiskStyrke(){
    return styrke;
  }
  public String toString(){
    return "PreparatA narkotisk: pris = " + pris + ", virkestoff = " + virkestoff +
    ", styrke =  " + this.styrke;
  }
}
