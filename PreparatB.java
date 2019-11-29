class PreparatB extends Legemiddel{
  protected int styrke;

  PreparatB(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public int hentVanedannedeStyrke(){
    return styrke;
  }

  public String toString(){
    return "PreparatB vanedannende: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff +
    ", styrke: " + this.styrke;
  }
}
