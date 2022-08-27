class UbicacionPalabra {

  int oracion;
  int indice;
  int texto;

  UbicacionPalabra(int texto, int indice, int oracion){
    this.texto = texto;
    this.oracion = oracion;
    this.indice = indice;
  }

  public String toString(){
    return "{oracion: "+oracion+"-indice: "+indice+"}";
  } 
}
