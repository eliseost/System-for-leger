import java.util.Iterator;

class Lenkeliste<T> implements Liste<T>{

  protected class Node{
    protected T data;
    Node neste = null;

    Node(T x){
      data = x;
    }
  }

  protected class LenkelisteIterator implements Iterator<T>{

    protected Lenkeliste<T> minLenkeListe;
    Node crnt = start;

    public LenkelisteIterator(Lenkeliste<T> lenkeliste){
      minLenkeListe = lenkeliste;
    }

    public boolean hasNext(){
      return crnt != null;
    }

    public T next(){
      Node tmp = crnt;
      crnt = crnt.neste;
      return tmp.data;
    }
  }

  protected Node start = null;


  public Iterator<T> iterator(){
    return new LenkelisteIterator(this);
  }


  public int stoerrelse(){
    int str = 0;
    Node p = start;

    while(p != null){
      str++;
      p = p.neste;
    }
    return str;
  }


  public boolean erTom(){
    if(start == null){
      return true;
    }
    return false;
    }


  public void leggTil(int pos, T x){
    if(pos < 0 || pos > stoerrelse()){
      throw new UgyldigListeIndeks(pos);
    }
    Node nyNode = new Node(x);

    //Hvis lista er tom må posisjonen være 0 for at det skal gå.
    if(erTom() && pos == 0){
      start = nyNode;
    }
    else if(erTom() && pos != 0){
      throw new UgyldigListeIndeks(0);
    }
    //Lista er ikke tom, legger til på posisjon.
    else{
      if(pos == 0){
        nyNode.neste = start;
        start = nyNode;
      }
      else{
        Node p = start;

        for(int i = 1; i < pos; i++){
          p = p.neste;
        }
        nyNode.neste = p.neste;
        p.neste = nyNode;
      }
    }
  }


  public void leggTil(T x){
    Node nyNode = new Node(x);

    if(erTom()){
      start = nyNode;
    }
    else{
      //Ikke tom, legger da til på slutten av lista
      Node p = start;
      while (p.neste != null){
        p = p.neste;
      }
      p.neste = nyNode;
    }
  }


  public void sett(int pos, T x){
    if(pos < 0 || pos >= stoerrelse()){
      throw new UgyldigListeIndeks(pos);
    }
    //Er lista tom kan du ikke endre data på et element.
    if(erTom()){
      throw new UgyldigListeIndeks(0);
    }
    else{
      Node p = start;

      for(int i = 0; i < pos; i++){
        p = p.neste;
      }
      p.data = x; //Erstatter dataen som var i denne posisjonen med x.
    }
  }


  public T hent(int pos){
    if(pos < 0 || pos >= stoerrelse()){
      throw new UgyldigListeIndeks(pos);
    }
    if(erTom()){
      throw new UgyldigListeIndeks(0);
    }

    Node p = start;

    for(int i = 0; i < pos; i++){
      p = p.neste;
    }
    T ret = p.data;
    return ret;
  }


  public T fjern(int pos){
    if(pos < 0 || pos >= stoerrelse()){
      throw new UgyldigListeIndeks(pos);
    }
      //Hvis første elementet skal fjernes, kalles metoden fjern (som fjerner første element)
      //og dette elementet fjernes og returneres
    if(pos == 0){
      return fjern();
    }
    else{
      Node p = start;

      for(int i = 1; i < pos; i++){
        p = p.neste;
      }
      Node retur = p.neste;
      p.neste = retur.neste;
      return retur.data;
    }
  }


  public T fjern(){
    if(erTom()){
      throw new UgyldigListeIndeks(0);
    }
    Node retur = start;
    start = start.neste;

    return retur.data;
  }
}
