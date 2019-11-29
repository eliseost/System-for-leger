class Spesialist extends Lege implements Godkjenningsfritak{
  protected int kontrollID;

  Spesialist(String navn, int kontrollID){
    super(navn);
    this.kontrollID = kontrollID;
  }

  public int hentKontrollID(){
    return kontrollID;
  }
  @Override
  public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit){

    Resept nyResept = new Hvitresept(legemiddel, this, pasient, reit);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }

  @Override
  public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit){

    Resept nyResept = new Blaaresept(legemiddel, this, pasient, reit);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }
  @Override
  public Resept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit){
    Resept nyResept = new Militaerresept(legemiddel, this, pasient, reit);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }
  @Override
  public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient){

    Resept nyResept = new PResept(legemiddel, this, pasient);
    utskrevedeResepter.leggTil(nyResept);
    pasient.leggTilResept(nyResept);

    return nyResept;
  }

  public String toString(){
    return "Lege: " + navn + " er spesialist med kontrollID: " + kontrollID;
  }
}
