abstract class Resept{
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected Pasient pasient;
  protected int reit;
  static int id = 0;
  protected int minId;

  Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasient = pasient;
    this.reit = reit;
    minId = id;
    id++;
  }
  public void leggTilIPasientListe(){
    pasient.leggTilResept(this);
  }

  public int hentId(){
    return minId;
  }

  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  public Lege hentLege(){
    return utskrivendeLege;
  }

  public Pasient hentPasient(){
    return pasient;
  }

  public int hentReit(){
    return reit;
  }

  public boolean brukResept(){
    if(!bruk()){
      System.out.println("Kunne ikke bruke resept, ingen reit igjen.");
      return false;
    }else{
      reit--;
      return true;
    }
  }

  public boolean bruk(){
    if (reit < 1){
      return false;
    }
    else{
      return true;
    }
  }

  public String toString(){
    return "Resept på " + legemiddel.hentNavn() + "med " + reit  + "reit, "+ "til " + pasient.hentNavn() + "skrevet ut av " + utskrivendeLege.hentNavn();
  }

  abstract public String farge();
  abstract public double prisAaBetale();
}
