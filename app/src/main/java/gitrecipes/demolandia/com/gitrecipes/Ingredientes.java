/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitrecipes.demolandia.com.gitrecipes;

public class Ingredientes {

    String ingredientes;
    String cantidad;

    public Ingredientes() {
        ingredientes = "";
        cantidad = "";

    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Ingredientes{" + "ingredientes=" + ingredientes + ", cantidad=" + cantidad + '}';
    }

    
    

}
