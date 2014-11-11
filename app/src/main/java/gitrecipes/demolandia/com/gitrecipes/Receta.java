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
public class Receta {

    String receta;
    String pasos; 
    String idUnico;
    private static Ingredientes[] ingrediente = new Ingredientes[0];

    public Receta() {
        receta = "";
        pasos = "";

    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public void setIngrediente(Ingredientes ing){
        Ingredientes[] listaAux = new Ingredientes[ingrediente.length + 1];

        for (int i = 0; i < ingrediente.length; i++) {
            listaAux[i] = ingrediente[i];
        }
        listaAux[listaAux.length - 1] = ing;
        ingrediente = listaAux;
    }

    @Override
    public String toString() {
        return "Receta{" + "receta=" + receta + ", pasos=" + pasos + '}';
    }





}
