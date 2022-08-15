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
public class Conta implements Serializable {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private static int totalContas = 0;

    
    public Conta(String nome) {

        this.setNome(nome);
        this.id = totalContas + 1;
        totalContas++;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.contains("0") || nome.contains("1")) {
            System.out.println("Erro: Nome Inválido");
            return;
        }

        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("ERRO: Email Inválido");

        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {

        return this.id + ":" + this.nome + ":" + this.email + ":" + this.senha;
    }
}
