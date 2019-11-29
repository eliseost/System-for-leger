class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  @Override
  public void leggTil(T x){
    Node nyNode = new Node(x);

    //Er lista tom legges noden til.
    if(erTom()){
      start = nyNode;
    }

    //Er den ikke tom må lista gås igjennom og finner om den nye noden er
    //mindre enn noen av de ekstisterende nodene
    else{
      Node p = start;
      for(int i = 0; i < stoerrelse(); i++){
        if(p.data.compareTo(nyNode.data) > 0){
          //nyNode mindre, settes inn før p
          //hvis det skal setter inn som første i lista:
          if(start == p){
            nyNode.neste = p;
            start = nyNode;
            return;
          }
          else{
            Node q = start;
            for(int j = 0; j < i-1; j++){
              q = q.neste;
            }
            nyNode.neste = q.neste;
            q.neste = nyNode;
            return;
          }
        }
        //Var ikke nynode mindre enn p, blir p neste og forløkka tar en runde til.
        p = p.neste;
      }
      //Ny node størst, settes inn bakerst.
      super.leggTil(x);
    }
  }

  @Override
  public T fjern(){
    if(erTom()){
      throw new UgyldigListeIndeks(0);
    }
    //Hvis lista er kun et element
    else if(stoerrelse() == 1){
      Node retur = start;
      start = null;
      return retur.data;
    }
    else{
      Node p = start;

      for(int i = 1; i < stoerrelse()-1; i++){
        p = p.neste;
      }
      Node retur = p.neste;
      T data = retur.data;
      p.neste = null;
      return data;
    }
  }

  @Override
  public void sett(int pos, T x){
    throw new UnsupportedOperationException("Kan ikke bruke metoden");
  }

  @Override
  public void leggTil(int pos, T x){
    throw new UnsupportedOperationException("Kan ikke bruke metoden");
  }
}
