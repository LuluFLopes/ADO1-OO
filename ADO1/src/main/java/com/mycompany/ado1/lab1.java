package com.mycompany.ado1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class Description . . .
 *
 * @author: Autor
 * @version: 1.0 Main Class File: topAXX.java File: Structure.java Date:
 * DD/MM/YYYY
 */
public class lab1 {

    public static void main(String[] args) {

        // nome do arquivo
        String nomeDoArquivo1 = "pib.txt";
        String nomeDoArquivo2 = "regioes.txt";

        // linha temporaria
        String linha = null;

        ArrayList<Estados> lista = new ArrayList<Estados>();
        Regioes rg = new Regioes();
        Estados estado = new Estados();
        double total = 0;


        /*      ------------------------------------- */
 /*      Abertura de arquivo e loop de leitura */
 /*      ------------------------------------- */
        try {

            BufferedReader bufferedReader = leArquivo(nomeDoArquivo1);

            // loop por cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                String pib = linha.split(";")[1];
                String nome = linha.split(";")[0];
                double pibConvertido = Double.parseDouble(pib);
                rg.adicionaArray(nome, pibConvertido);
                total += pibConvertido;

            }
            
            System.out.println(total);

            lista = rg.getLista();

            for (int i = 0; i < lista.size(); i++) {

                estado = lista.get(i);
                estado.setPercentual(rg.conta(estado.getPib(), total));

//                System.out.println(es.getNome());
//                System.out.println(es.getPib());
//                System.out.printf("Percentual: %.2f\n", es.getPercentual());
            }

            // feche o arquivo
            bufferedReader.close();

            bufferedReader = leArquivo(nomeDoArquivo2);

            // loop por cada linha do arquivo
            HashMap<String, List<String>> listaRegioes = new HashMap<>();
            int contador = 0;
            String regiao = "";

            while ((linha = bufferedReader.readLine()) != null) {

                if (contador == 0) {
                    regiao = linha;
                    listaRegioes.put(regiao, new ArrayList<>());
                } else if (linha.isBlank()) {
                    regiao = bufferedReader.readLine();
                    listaRegioes.put(regiao, new ArrayList<>());
                } else {
                    listaRegioes.get(regiao).add(linha);
                }

                contador++;
            }

            for (int i = 0; i < lista.size(); i++) {

                estado = lista.get(i);

                for (var regiaoAtual : listaRegioes.entrySet()) {

                    // regiaoAtual = Norte=[Rond�nia, Acre, Amazonas, Roraima, Par�, Amap�, Tocantins];
                    if (regiaoAtual.getValue().contains(estado.getNome())) {
                        contador = 0;
                        estado.setRegiao(regiaoAtual.getKey());

                    }

                }

//                System.out.println(estado.getNome());
//                System.out.println(estado.getRegiao());  
//                System.out.println("");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo1 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo1 + "'");
        }

        //                es = lista.get(contador);
//                contador++;
//                if (es.getNome() == regiao) {
//                    es.setRegiao(regiao);
//                }

        /*      ------------------------------------- */
 /*      Exemplo de escrita em arquivo         */
 /*      ------------------------------------- */
        String arquivoDeSaida = "saida.txt";

        try {
            
            String norte = "";
            String sul = "";
            String nordeste = "";
            String sudeste = "";
            String centro = "";
            
            double pibNorte = 0;
            double pibSul = 0;
            double pibNordeste = 0;
            double pibSuldeste = 0;
            double pibCentro = 0;

            FileWriter fileWriter = new FileWriter(arquivoDeSaida);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < lista.size(); i++) {

                estado = lista.get(i);

                if (estado.getRegiao().equals("Norte")) {
                    
                    norte = "norte";
                    pibNorte += estado.getPib();

                } else if (estado.getRegiao().equals("Nordeste")) {
                    
                    nordeste = "nordeste";
                    pibNordeste += estado.getPib();

                } else if (estado.getRegiao().equals("Sul")) {
                    
                    sul = "sul";
                    pibSul += estado.getPib();

                } else if (estado.getRegiao().equals("Sudeste")) {
                    
                    sudeste = "sudeste";
                    pibSuldeste += estado.getPib();

                } else if (estado.getRegiao().equals("Centro-Oeste")) {
                    
                    centro = "centro-oeste";
                    pibCentro += estado.getPib();

                }

            }

            bufferedWriter.write("pib da região " + norte + " = " + pibNorte);
            bufferedWriter.newLine();
            bufferedWriter.write("pib da região " + nordeste + " = " + pibNordeste);
            bufferedWriter.newLine();
            bufferedWriter.write("pib da região " + sul + " = " + pibSul);
            bufferedWriter.newLine();
            bufferedWriter.write("pib da região " + sudeste + " = " + pibSuldeste);
            bufferedWriter.newLine();
            bufferedWriter.write("pib da região " + centro + " = " + pibCentro);
            bufferedWriter.newLine();
            
            // feche o arquivo
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Erro de escrita em '" + arquivoDeSaida + "'");
        }

    }

    public static BufferedReader leArquivo(String arquivo) throws FileNotFoundException {

        FileReader fileReader = new FileReader(arquivo);
        BufferedReader bufferedReader;

        return bufferedReader = new BufferedReader(fileReader);

    }
}
