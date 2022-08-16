class UbicacionPalabra {
    
    int oracion;
    int indice;
    
    UbicacionPalabra(int indice, int oracion){
        this.oracion = oracion;
        this.indice = indice;
    }
    
    public String toString(){
        return "{oracion: "+oracion+"-indice: "+indice+"}";
    } 
}
