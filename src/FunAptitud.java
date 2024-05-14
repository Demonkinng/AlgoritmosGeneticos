import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class FunAptitud extends FitnessFunction {
    @Override
    protected double evaluate(IChromosome ic) { // permite crear la funcion de euristica
        double score = 0;
        int sx = (int) ic.getGene(0).getAllele(); // del individuo obtengo el gen de la posicion cero, en este caso el signo de x
        int sy = (int) ic.getGene(6).getAllele(); // del individuo obtengo el gen de la posicion uno, en este caso el signo de y
        // Despedazo el cromosoma en partes para obtener algo
        String valorX = (int) ic.getGene(1).getAllele() + "" +
                        (int) ic.getGene(2).getAllele() + "" +
                        (int) ic.getGene(3).getAllele() + "" +
                        (int) ic.getGene(4).getAllele() + "" +
                        (int) ic.getGene(5).getAllele();
        String valorY = (int) ic.getGene(7).getAllele() + "" +
                        (int) ic.getGene(8).getAllele() + "" +
                        (int) ic.getGene(9).getAllele() + "" +
                        (int) ic.getGene(10).getAllele() + "" +
                        (int) ic.getGene(11).getAllele();

        int valX = Integer.parseInt(valorX, 2); // convierto el valor binario a entero
        int valY = Integer.parseInt(valorY, 2); // convierto el valor binario a entero
        if(sx == 0) valX = valX * -1; // si el signo de x es 0, entonces el valor de x es negativo
        if(sy == 0) valY = valY * -1; // si el signo de y es 0, entonces el valor de y es negativo

        score = (valX * valX * valX) + (valY * valY * valY * valY); // se calcula el puntaje acorde a la funcion de aptitud X^3 + Y^4
        if (score < 0) score = 0;
        //if (score < 0) score = score*-1; // si el puntaje es negativo, se le asigna 0
        return score;
    }
}

// Notas:
// importante graficar la longitud del gen para una mejor visualizacion
// la descomposicion me lleva a algo
// ojo que la ecuacion puede ser un insumo de otra funcion (ejm: numeros primos del score)