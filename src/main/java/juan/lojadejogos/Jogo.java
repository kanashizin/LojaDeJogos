/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juan.lojadejogos;

/**
 *
 * @author SadBo
 */
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class Jogo implements Comparable ,Serializable {

    private int id;
    private String nome;
    private String sinopse;
    private double preco;
    private String genero;
    private String classificacaoIndicativa;
   /* private static int totalJogosApagados = 0;
    private int apagadosTotal;

    public static int getTotalJogosApagados() {
        return totalJogosApagados;
    }

    public static void addTotalJogosApagados() {
        Jogo.totalJogosApagados = totalJogosApagados + 1;
    }*/
    private static int totalJogos = 0;

    @Override
    public int compareTo(Object obj) {
        Jogo outroJogo = (Jogo) obj;
        
        if (this.getId() > outroJogo.getId()) {
            return 1;
        } else {
            return -1;
        }

    }
        public Jogo ( String nome) {
        this.setNome(nome);
        this.id = totalJogos + 1;
        totalJogos++;
        //apagadosTotal = totalJogosApagados;
    }

      /*public static void setTotalJogosApagados(int num) {
        Jogo.totalJogosApagados =  num;
    }
    public int getApagadosTotal() {
        return apagadosTotal;
    }*/
     
    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public static int getTotalJogos() {
        return totalJogos;
    }

    public static void setTotalJogos(int totalJogos) {
        Jogo.totalJogos = totalJogos;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.contains("0") || nome.contains("1")) {
            System.out.println("Nome Inválido");
            System.out.println("Digite novamente:");
            return;
        }
        this.nome = nome;

    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {

        return this.id + " : " + this.nome;

    }
}
