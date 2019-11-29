class Stabel<T> extends Lenkeliste<T>{

  public void leggPaa(T x){
    leggTil(x);
  }

  public T taAv(){
    int siste = this.stoerrelse() - 1;
    return fjern(siste);
  }
}
