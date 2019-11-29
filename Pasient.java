class Pasient{

  private String navn;
  private String fodselsnr;
  private static int id = 0;
  private int minID;
  private Lenkeliste<Resept> liste;


  public Pasient(String n, String nr){
    navn = n;
    fodselsnr = nr;
    minID = id;
    id++;
    liste = new Lenkeliste<Resept>();
  }

  public void leggTilResept(Resept r){
    liste.leggTil(r);
  }

  public Lenkeliste<Resept> hentReseptListe(){
    return liste;
  }
  public String hentNavn(){
    return navn;
  }

  public String hentNr(){
    return fodselsnr;
  }

  public int hentID(){
    return minID;
  }
}
