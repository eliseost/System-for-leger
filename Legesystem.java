import java.util.*;
import java.io.*;

public class Legesystem{
    // Opprett lister som lagrer objektene i legesystemet
    private static Lenkeliste<Pasient> pasientliste = new Lenkeliste<Pasient>();
    private static Lenkeliste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
    private static Lenkeliste<Lege> legeliste = new SortertLenkeliste<Lege>();
    private static Lenkeliste<Resept> reseptliste = new Lenkeliste<Resept>();


    public static void main(String[] args) throws UlovligUtskrift{

      File fil = new File("fil.txt");
      lesFraFil(fil);

      String ferdig = "nei";

      while(ferdig.equals("nei")){
        Scanner a = new Scanner(System.in);
        System.out.println("Vil du skrive ut en fullstendig oversikt? Svar ja eller nei: ");
        String svar = a.nextLine();
        if(svar.equals("ja")){
          skrivUtLegesystem();
        }
        leggTilElement();
        brukResept();

        System.out.println("Vil du se statistikk for legesystemet? Svar ja eller nei ");
        svar = a.nextLine();

        if(svar.equals("ja")){
          antallResept();
          narkotikaLeger();
          narkotikaPasienter();
        }
        System.out.println("Er du ferdig? ");
        ferdig = a.nextLine();
      }
    }

    private static void lesFraFil(File fil) throws UlovligUtskrift{
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
            return;
        }

        String innlest = scanner.nextLine();


        while(scanner.hasNextLine()){

            String[] info = innlest.split(" ");

            // Legger til alle pasientene i filen
            if(info[1].compareTo("Pasienter") == 0){
                while(scanner.hasNextLine()) {
                    innlest = scanner.nextLine();

                    //Om vi er ferdig med å legge til pasienter, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til legemiddler
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    //
                    //MERK:  Her må du legge til pasienten i en lenkeliste
                    //
                    String[] pasient = innlest.split(", ");
                    Pasient pasienten = new Pasient(pasient[0], pasient[1]);
                    pasientliste.leggTil(pasienten);
                }

            }
            //Legger inn Legemidlene
            else if(info[1].compareTo("Legemidler") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til legemidler, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til leger
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    String[] legemiddel = innlest.split(", ");
                    if(legemiddel[1].compareTo("a") == 0){
                        //
                        //MERK:  Her må du legge til et PreparatA i en lenkeliste
                        //
                        Double pris = Double.parseDouble(legemiddel[2]);
                        Double virkestoff = Double.parseDouble(legemiddel[3]);
                        int reit = Integer.parseInt(legemiddel[4]);
                        Legemiddel prepA = new PreparatA(legemiddel[0], pris, virkestoff, reit);
                        legemiddelliste.leggTil(prepA);
                    }
                    else if(legemiddel[1].compareTo("b") == 0){
                        //
                        //MERK:  Her må du legge til et PreparatB i en lenkeliste
                        //
                        Double pris = Double.parseDouble(legemiddel[2]);
                        Double virkestoff = Double.parseDouble(legemiddel[3]);
                        int reit = Integer.parseInt(legemiddel[4]);
                        Legemiddel prepB = new PreparatB(legemiddel[0], pris, virkestoff, reit);
                        legemiddelliste.leggTil(prepB);
                    }else if (legemiddel[1].compareTo("c") == 0){
                        //
                        //MERK:  Her må du legge til et PreparatC i en lenkeliste
                        //
                        Double pris = Double.parseDouble(legemiddel[2]);
                        Double virkestoff = Double.parseDouble(legemiddel[3]);
                        Legemiddel prepC = new PreparatC(legemiddel[0], pris, virkestoff);
                        legemiddelliste.leggTil(prepC);
                    }

                }
            }
            //Legger inn leger
            else if(info[1].compareTo("Leger") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til leger, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til resepter
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    info = innlest.split(", ");
                    int kontrollid = Integer.parseInt(info[1]);
                    if(kontrollid == 0){
                        //
                        //MERK:  Her må du legge til et lege objekt i en sortert lenkeliste
                        //
                        Lege lege = new Lege(info[0]);
                        legeliste.leggTil(lege);

                    }else{
                        //
                        //MERK:  Her må du legge til et spesialist objekt i en sortert lenkeliste
                        //
                        Lege spesialist = new Spesialist(info[0], kontrollid);
                        legeliste.leggTil(spesialist);
                    }
                }
            }
            //Legger inn Resepter
            else if(info[1].compareTo("Resepter") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    info = innlest.split(", ");

                    //
                    // Her må du finne legen, legemiddelet, og pasienten som ligger
                    // i lenkelistene utifra informasjonen.
                    //
                    // Dette burde skilles ut i hjelpemetoder leter gjennom listene
                    // og returnerer riktig objekt, ut ifra informasjonen som ble lest inn
                    Legemiddel legemiddelet = finnLegemiddel(Integer.parseInt(info[0]));
                    Pasient pasienten = finnPasient(Integer.parseInt(info[2]));
                    Lege legen = finnLege(info[1]);


                    if(legemiddelet == null){
                      System.out.println("Legemiddelet finnes ikke.");
                      return;
                    }
                    if(pasienten == null){
                      System.out.println("Pasienten finnes ikke.");
                      return;
                    }
                    if(legen == null){
                      System.out.println("Legen finnes ikke.");
                      return;
                    }


                    Resept resepten = legen.skrivHvitResept(legemiddelet, pasienten, Integer.parseInt(info[3]));
                    reseptliste.leggTil(resepten);

                    //
                    // Opprett et reseptobjekt med skrivResept funksjonen i legen,
                    // og legg det til i en lenkeliste
                    //
                    // Dersom legeobjektene dine oppretter PResepter, kan du ignorere reit
                    //
                    //
                }
            }
        }
    }

    public static Legemiddel finnLegemiddel(int id){

      if(id < 0 || id >= legemiddelliste.stoerrelse()){
        throw new UgyldigListeIndeks(id);
      }
      Legemiddel rett = null;

      for (Legemiddel lm: legemiddelliste){
        if (id == lm.hentID()){
          rett = lm;
        }
      }return rett;
    }

    public static Pasient finnPasient(int id){

      if(id < 0 || id > pasientliste.stoerrelse()){
        throw new UgyldigListeIndeks(id);
      }
      Pasient rett = null;

      for (Pasient p: pasientliste){
        if (id == p.hentID()){
          rett = p;
        }
      }
      return rett;
    }

    public static Lege finnLege(String navn){

      Lege rett = null;
      for (Lege lege: legeliste){
        if (lege.hentNavn().equals(navn)){
          rett = lege;
        }
      }
      return rett;
    }

    public static Resept finnResept(int id){

      if(id < 0 || id > reseptliste.stoerrelse()){
        throw new UgyldigListeIndeks(id);
      }

      Resept rett = null;
      for (Resept resept: reseptliste){
        if (id == resept.hentId()){
          rett = resept;
        }
      }
      return rett;
    }

    public static void skrivUtLegesystem(){

      System.out.println("Foelgende leger finnes i systemet: ");
      for (Lege l : legeliste){
        System.out.println(l.hentNavn());
      }

      System.out.println("Foelgende pasienter finnes i systemet: ");
      for (Pasient p : pasientliste){
        System.out.println(p.hentNavn());
      }

      System.out.println("Foelgende legemiddler finnes i systemet: ");
      for(Legemiddel l : legemiddelliste){
        System.out.println(l.hentNavn());
      }

      System.out.println("Foelgende resepter finnes i systemet: ");
      for (Resept r : reseptliste){
        System.out.println(r);
      }
    }

    private static void leggTilElement() throws UlovligUtskrift{

      Scanner b = new Scanner(System.in);

      System.out.println("Vil du legge til ett nytt element til systemet? Svar ja eller nei: ");
      String svar = b.nextLine();

      if(svar.equals("ja")){
        System.out.println("Hva vil du legge til av: ");
        System.out.println("0: Pasient");
        System.out.println("1: Lege");
        System.out.println("2: Legemiddel");
        System.out.println("3: Resept");
        String leggeTil = b.nextLine();

        if(leggeTil.equals("0")){
          //Legge til pasient. Trenger navn og fødselsnummer.
          System.out.println("Hva er navnet til pasienten? ");
          String navn = b.nextLine();
          System.out.println("Hva er fødselsnummeret til pasienten? ");
          String nr = b.nextLine();
          leggTilPasient(navn, nr);

        }else if(leggeTil.equals("1")){
          //Legger til lege, trenger navn og id.
          System.out.println("Hva er navnet til legen? ");
          String navn = b.nextLine();
          System.out.println("Hva er id'en til legen? ");
          svar = b.nextLine();
          int id = Integer.parseInt(svar);
          leggTilLege(navn, id);

        }else if(leggeTil.equals("2")){
          //Legge til legemiddel. Trenger navn, pris og virkestoff.
          System.out.println("Hva er navnet til legemiddelet? ");
          String navn = b.nextLine();

          System.out.println("Hva er prisen til legemiddelet? ");
          svar = b.nextLine();
          Double pris = Double.parseDouble(svar);

          System.out.println("Hva er virketoffet til legemiddelet? ");
          svar = b.nextLine();
          Double virkestoff = Double.parseDouble(svar);

          System.out.println("Hva er styrken til legemiddelet? ");
          svar = b.nextLine();
          int styrke = Integer.parseInt(svar);

          System.out.println("Hvilket preparat er legemiddelet, a, b eller c? ");
          String preparat = b.nextLine();
          leggTilLegemiddel(navn, pris, virkestoff, styrke, preparat);

        }else if (leggeTil.equals("3")){
          //Legger til resept. Trenger legemiddel, lege, pasient og reit.
          //finnLegemiddel går på id, kan ikke bruke den.
          System.out.println("Hva er navnet til legemiddelet? ");
          String navn = b.nextLine();
          Legemiddel legemiddel = null;
          for(Legemiddel lm : legemiddelliste){
            if(navn.equals(lm.hentNavn())){
              legemiddel = lm;
            }
          }
          if(legemiddel == null){
            System.out.println("Legemiddelet finnes ikke i lista og er ikke gyldig.");
            return;
          }

          System.out.println("Hva er navnet til legen? ");
          navn = b.nextLine();
          Lege lege = finnLege(navn);

          if(lege == null){
            System.out.println("Legen finnes ikke i lista og er ikke gyldig.");
            return;
          }

          System.out.println("Hva er navnet til pasienten? ");
          //Kan ikke bruke finnPasient for bruker navn ikke id.
          navn = b.nextLine();
          Pasient pasient = null;
          for(Pasient p : pasientliste){
            if(navn.equals(p.hentNavn())){
              pasient = p;
            }
          }

          if(pasient == null){
            System.out.println("Pasienten finnes ikke i lista og er ikke gyldig.");
            return;
          }

          System.out.println("Hvor mange reit har resepten? ");
          svar = b.nextLine();
          int reit = Integer.parseInt(svar);

          leggTilResept(lege, legemiddel, pasient, reit);
        }
      }
    }

    public static void leggTilLege(String navn, int kontroll){
      Lege lege = null;
      if(kontroll == 0){
        lege = new Lege(navn);
      }else{
        lege = new Spesialist(navn, kontroll);
      }

      for(Lege l : legeliste){
        if (l.hentNavn().equals(lege.hentNavn())){
          System.out.println("Denne legen finnes fra før");
          break;
        }
      }
      legeliste.leggTil(lege);
    }

    public static void leggTilPasient(String navn, String nr){
      Pasient pasient = new Pasient(navn, nr);

      for(Pasient p : pasientliste){
        if(p.hentNr() == pasient.hentNr()){
          System.out.println("Denne pasienten finnes fra før");
          break;
        }
      }
      pasientliste.leggTil(pasient);
    }

    public static void leggTilLegemiddel(String navn, Double pris, Double virkestoff, int styrke, String preparat){
      Legemiddel lm = null;

      if (preparat.equals("a")){
        lm = new PreparatA(navn, pris, virkestoff, styrke);
      }
      else if(preparat.equals("b")){
        lm = new PreparatB(navn, pris, virkestoff, styrke);
      }
      else if(preparat.equals("c")){
        lm = new PreparatC(navn, pris, virkestoff);
      }
      else{
        System.out.println("Ulovlig preparat gitt.");
        return;
      }
      for(Legemiddel l : legemiddelliste){
        if (l.hentNavn().equals(lm.hentNavn())){
          System.out.println("Dette legemiddelet finnes fra før");
          return;
        }
      }
      legemiddelliste.leggTil(lm);
    }

    public static void leggTilResept(Lege lege, Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
      boolean lFinnes = false;

      for(Lege l : legeliste){
        if(l.hentNavn().equals(lege.hentNavn())){
          lFinnes = true;
        }
      }
      if(!lFinnes){
        System.out.println("Legen finnes ikke i lista og er ikke gyldig.");
        return;
      }

      boolean lmFinnes = false;

      for(Legemiddel l : legemiddelliste){
        if(l.hentNavn().equals(legemiddel.hentNavn())){
          lmFinnes = true;
        }
      }
      if(!lmFinnes){
        System.out.println("Legemiddelet finnes ikke i lista og er ikke gyldig.");
        return;
      }

      boolean pFinnes = false;

      for(Pasient p : pasientliste){
        if(p.hentNr() == pasient.hentNr()){
          pFinnes = true;
        }
      }
      if(!pFinnes){
        System.out.println("Pasienten finnes ikke i lista og er ikke gyldig.");
        return;
      }

      Resept nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
      reseptliste.leggTil(nyResept);

    }

    private static void brukResept(){
      Scanner c = new Scanner(System.in);
      System.out.println("Vil du bruke en resept? Svar ja eller nei: ");
      String svar = c.nextLine();

      if(svar.equals("ja")){

        System.out.println("Hvem sin resept vil du bruke?");
        int nr = 0;
        for (Pasient p : pasientliste){
          System.out.println(nr + ": " + p.hentNavn() + "(fnr " + p.hentNr() + ").");
          nr ++;
        }
        //Svaret er det samme som pasientens id.

        svar = c.nextLine();
        int id = Integer.parseInt(svar);

        Pasient pasient = finnPasient(id);
        System.out.println("Valgt pasient: " + pasient.hentNavn());

        System.out.println("Hvilken resept vil du bruke? ");
        Lenkeliste<Resept> liste = pasient.hentReseptListe();
        nr = 0;
        for (Resept r : liste){
          Legemiddel l = r.hentLegemiddel();
          System.out.println(nr + ": " + l.hentNavn() + "(" + r.hentReit() + ").");
          nr ++;
        }

        svar = c.nextLine();
        int plass = Integer.parseInt(svar);

        Resept resept = null;
        nr = 0;

        for(Resept r : liste){
          if(plass == nr){
            resept = r;
          }
          nr++;
        }

        Legemiddel lm = resept.hentLegemiddel();
        boolean bruk = resept.brukResept();
        int reit = resept.hentReit();
        if(bruk){
          System.out.println("Brukte resept paa " + lm.hentNavn() + ". Antall gjenvaerende reit: " + reit);
        }
      }
    }

    private static void antallResept(){
      int antallNarkotisk = 0;
      int antallVane = 0;

      for(Resept r : reseptliste){
        Legemiddel l = r.hentLegemiddel();
        if(l instanceof PreparatA){
          antallNarkotisk ++;
        }
        if(l instanceof PreparatB){
          antallVane ++;
        }
      }
      System.out.println("Totalt utskrevne resepter paa vanedannende legemidler: " + antallVane);
      System.out.println("Totalt utskrevne resepter paa narkotiske legemidler: " + antallNarkotisk);
    }

    public static void narkotikaLeger(){

      for(Lege leger : legeliste){
        int antallNarkotika = 0;
        Lenkeliste<Resept> resepter = leger.hentReseptListe();

        for(Resept r : resepter){
          Legemiddel l = r.hentLegemiddel();
          if(l instanceof PreparatA){
            antallNarkotika ++;
          }
        }
        if(antallNarkotika > 0){
          System.out.println(leger.hentNavn() + " har skrevet ut " + antallNarkotika + " resept(er) til narkotiske legemiddler.");
        }
      }
    }

    public static void narkotikaPasienter(){

      for(Pasient pasienter : pasientliste){
        int antallNarkPas = 0;
        Lenkeliste<Resept> resepter = pasienter.hentReseptListe();

        for(Resept r : resepter){
          Legemiddel l = r.hentLegemiddel();
          if(l instanceof PreparatA){
            if(r.hentReit() > 0){
              antallNarkPas ++;
            }
          }
        }
        if(antallNarkPas > 0){
          System.out.println(pasienter.hentNavn() + " har " + antallNarkPas + " resept(er) til narkotiske legemiddler.");
        }
      }
    }
}
