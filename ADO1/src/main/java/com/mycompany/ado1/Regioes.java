package com.mycompany.ado1;

import java.util.ArrayList;

/**
 *
 * @author lucme
 */
public class Regioes {

    private String[] estado;
    private double[] pibTotal;
    private ArrayList<Estados> lista = new ArrayList<Estados>();
    private Estados es;
    private static int contador = 0;

    public void calcularPib() {

    }

    public void adicionaArray(String nome, double pib) {
        es = new Estados();
        es.setNome(nome);
        es.setPib(pib);
        lista.add(contador, es);
        contador++;
    }

    public double conta(double pib, double total) {
        return (pib * 100) / total;
    }

    public ArrayList<Estados> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Estados> lista) {
        this.lista = lista;
    }

}
