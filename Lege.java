class Lege implements Comparable<Lege>{
  protected String navn;
  protected Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();

  Lege(String navn){
    this.navn = navn;
  }

  public String hentNavn(){
    return navn;
  }

  public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    if (legemiddel instanceof PreparatA){
      throw new UlovligUtskrift(this, legemiddel);
    }

    Resept nyResept = new Hvitresept(legemiddel, this, pasient, reit);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }

  public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    if (legemiddel instanceof PreparatA){
      throw new UlovligUtskrift(this, legemiddel);
    }

    Resept nyResept = new Blaaresept(legemiddel, this, pasient, reit);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }

  public Resept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    if (legemiddel instanceof PreparatA){
      throw new UlovligUtskrift(this, legemiddel);
    }

    Resept nyResept = new Militaerresept(legemiddel, this, pasient, reit);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }

  public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
    if (legemiddel instanceof PreparatA){
      throw new UlovligUtskrift(this, legemiddel);
    }

    Resept nyResept = new PResept(legemiddel, this, pasient);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }

  public Lenkeliste<Resept> hentReseptListe(){
    return utskrevedeResepter;
  }

  public int compareTo(Lege l){
    if(this.hentNavn().compareTo(l.hentNavn()) < 0){
      return -1;
    }else if(this.hentNavn().compareTo(l.hentNavn()) > 0){
      return 1;
    }
    return 0;
  }

  public String toString(){
    return "Lege: " + hentNavn();
  }
}
