/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitrecipes.demolandia.com.gitrecipes;

/**
 *
 * @author matias
 */
public abstract class ArrayRecetas {

    private static Receta[] recetas = new Receta[0];

    /**
     * Esta funcion agrega una nueva lista de candidatos al array
     *
     * @param receta
     * @return
     */
    public static void addReceta(Receta receta) {
        Receta[] listaAux = new Receta[recetas.length + 1];

        for (int i = 0; i < recetas.length; i++) {
            listaAux[i] = recetas[i];
        }
        listaAux[listaAux.length - 1] = receta;
        recetas = listaAux;
    }

    public static void borrarRecetas(int h) {
        Receta[] aux = new Receta[ArrayRecetas.getRecetas().length - 1];
        if (ArrayRecetas.getRecetas()[h] != null) {
            ArrayRecetas.getRecetas()[h] = null;
        }
        for (int i = 0; i < ArrayRecetas.getRecetas().length; i++) {
            if (ArrayRecetas.getRecetas()[i] != null) {
                for (int j = 0; j < aux.length; j++) {
                    if (aux[j] == null) {
                        aux[j] = ArrayRecetas.getRecetas()[i];
                        break;
                    }

                }
            }
        }
        recetas = aux;
    }

    /**
     * Devuelve el array de listas
     */
    public static Receta[] getRecetas() {
        return recetas;

    }

    static String buscarReceta(Receta[] args, String busc) {
        for (int i = 0; i <= args.length; i++) {

            if (args[i].getReceta() == busc) {
                return args[i].getReceta();

            }

        }
        return null;
    }
}
