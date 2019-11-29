class PreparatC extends Legemiddel{

  PreparatC(String navn, double pris, double virkestoff){
    super(navn, pris, virkestoff);
  }

  public String toString(){
    return "PreparatC: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff;
  }
}
