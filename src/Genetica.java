import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;


public class Genetica {
    public Configuration configurarAG(int tamanioPoblacion, int longGene) {
        try {
            Configuration conf = new DefaultConfiguration();// conf por defecto: 1. seleccion de los padres de manera aleatoria, 2. cruce por punto una del padre y una de la made aleatorio, 3. mutacion aleatoria de un solo gen
            conf.setFitnessFunction(new FunAptitud()); // se setea la funcion de aptitud
            conf.setPopulationSize(tamanioPoblacion); // indica el tamaño de la poblacion
            Chromosome ejemplo = new Chromosome(conf, genesLong(longGene, conf)); // se crea un cromosoma de ejemplo, el parametro 2 es un arreglo de genes
            conf.setSampleChromosome(ejemplo); // se setea el cromosoma de ejemplo
            return conf;
        } catch (InvalidConfigurationException e) {
            return null;
        }
    }

    // se crea una poblacion aleatoria
    // se crea un genotipo (Genotype) con la configuracion dada
    public Genotype get_Poblacion(Configuration conf) {
        try {
            return Genotype.randomInitialGenotype(conf); // se crea una poblacion con cantidad de cromosomas aleatoria
        } catch (InvalidConfigurationException e) {
            return null;
        }
    }

    /* se evoluciona la poblacion
    * se evoluciona la poblacion un numero de veces dado
    */
    public void evolucionar(Genotype poblacion, int numeroEvoluciones, int numeroIteraccion) {
        System.out.println("Poblacion Inicial: ");
        descomponerTodos(poblacion.getChromosomes());
        System.out.println("-----");
        for (int i = 0; i < numeroIteraccion; i++) { // se evoluciona la poblacion un numero de veces dado
            poblacion.evolve(numeroEvoluciones); // se evoluciona la poblacion
            System.out.println("Iteraccion: " + (i + 1));
            descomponerTodos(poblacion.getChromosomes());
            System.out.println("Mejor: ");
            descomponerIndividuo(poblacion.getFittestChromosome());
            System.out.println("-----");
            //poblacion.getFittestChromosome(); // se obtiene el cromosoma mas apto (mejor individuo)
        }
        System.out.println("Mejor de Todo: ");
        descomponerIndividuo(poblacion.getFittestChromosome());
    }

    // se descompone cada individuo de la poblacion
    private void descomponerTodos(IChromosome[] ics) {
        for (IChromosome ic : ics) {
            descomponerIndividuo(ic);
        }
    }

    // se descompone el individuo
    private void descomponerIndividuo(IChromosome ic) {
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
        System.out.println("X: " + valX + " ; Y: " + valY);
    }

    // se crea un gen de tipo entero, con un rango de 0 a 1 (binarios)
    // esto depende de como se quiere construir, en este caso se decidio por binarios
    private Gene[] genesLong(int longGene, Configuration conf) {
        Gene[] genes = new Gene[longGene];
        for (int i = 0; i < longGene; i++) {
            try {
                genes[i] = new IntegerGene(conf, 0, 1); // se crea un gen de tipo entero, con un rango de 0 a 1
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return genes;
    }
}

// nota
// la especie esta dada por la longitud del cromosoma
// el cromosoma esta definido por los genes (funcion matematica) (representacion en binario)
// el cromosoma y el gen necesitan saber la configuracion para poder ser creados y luego muten acorde a la misma
// a la poblacion se le conoce como genotipo (Genotype)