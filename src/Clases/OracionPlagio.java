class OracionPlagio {

  int textoPerteneciente;
  int oracionEnTexto;
  int oracionEnPlagio;
  int palabrasPlagiadas;

  OracionPlagio(int textoPerteneciente, int oracionEnTexto, int oracionEnPlagio, int palabrasPlagiadas){
    this.textoPerteneciente = textoPerteneciente;
    this.oracionEnTexto = oracionEnTexto;
    this.oracionEnPlagio = oracionEnPlagio;
    this.palabrasPlagiadas = palabrasPlagiadas;
  }
  public String toString(){
    return "La oracion pertenece al texto: "+textoPerteneciente+"\nOracion en el texto original: " + oracionEnTexto+"\nOracion en el texto Plagio: "+oracionEnPlagio+"\nCantidad de Palabras encontradas: "+ palabrasPlagiadas;
  }
}
