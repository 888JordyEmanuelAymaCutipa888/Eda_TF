class OracionPlagio {
    
  int oracionEnTexto;
  int oracionEnPlagio;
  int palabrasPlagiadas;

  OracionPlagio(int oracionEnTexto, int oracionEnPlagio, int palabrasPlagiadas){
    this.oracionEnTexto = oracionEnTexto;
    this.oracionEnPlagio = oracionEnPlagio;
    this.palabrasPlagiadas = palabrasPlagiadas;
  }
  public String toString(){
    return "Oracion en el texto original: " + oracionEnTexto+"\nOracion en el texto Plagio: "+oracionEnPlagio+"\nCantidad de Palabras encontradas: "+ palabrasPlagiadas;
  }
}
