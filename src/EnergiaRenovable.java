public class EnergiaRenovable {
    int id;
    String tipo;
    String pais;
    int anio;
    double produccion;
    double consumo;

    public EnergiaRenovable() {
    }

    public EnergiaRenovable(double produccion) {
        this.produccion = produccion;
    }

    public EnergiaRenovable(String pais, String tipo, int anio) {
        this.pais = pais;
        this.tipo = tipo;
        this.anio = anio;
    }

    public double calcularProduccion() {
        return produccion;
    }
    public double calcularConsumo() {
        return consumo;
    }
    public double calcularEmisiones(){
        return produccion*consumo*anio;
    }

}
