public class Main {
    public static void main(String[] args) {
        int tamanioPoblacion = 6;
        int longCromosoma = 12;
        int iteraccion = 5;
        int evoluciones = 8;
        Genetica genetica = new Genetica();
        genetica.evolucionar(genetica.get_Poblacion(genetica.configurarAG(tamanioPoblacion, longCromosoma)), evoluciones, iteraccion);
    }
}