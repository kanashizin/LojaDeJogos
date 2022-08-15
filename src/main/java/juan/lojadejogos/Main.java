/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package juan.lojadejogos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author acer
 */
public class Main {

    static ArrayList<Jogo> listaJogo;
    static ArrayList<Conta> listaContas;

    public static void escreveArquivo() {
        try {
            File file = new File("listacontas.obj");
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));

            for (int i = 0; i < listaContas.size(); i++) {
                objOutput.writeObject(listaContas.get(i));
            }
            objOutput.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //
    }

    public static void escreveArquivoJogo() {
        try {
            File file = new File("listajogos.obj");
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));

            for (int i = 0; i < listaJogo.size(); i++) {
                objOutput.writeObject(listaJogo.get(i));
            }
            objOutput.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void leArquivoJogo() {
        try {
            File file = new File("listajogos.obj");
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));

            Jogo b;
            b = (Jogo) objInput.readObject();

            while (b != null) {
                listaJogo.add(b);
                Jogo.setTotalJogos(b.getId());//meus jogos novos = total de jogos que foram adicionados. EX:id do ultimo jogo adicionados=3 total de jogos=3
                b = (Jogo) objInput.readObject();
                
            }
             // Jogo.setTotalJogosApagados(b.getApagadosTotal());
           
            objInput.close();

        } catch (Exception e) {

        }

    }

    public static void leArquivo() {
        try {
            File file = new File("listacontas.obj");
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));//ler objeto e adicionar lista contas

            Conta a;
            a = (Conta) objInput.readObject();

            while (a != null) {
                listaContas.add(a);
                a = (Conta) objInput.readObject();
            }

            objInput.close();

        } catch (Exception e) {

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        listaJogo = new ArrayList();
        listaContas = new ArrayList();
        leArquivoJogo();
        leArquivo();
        int opcao;
        boolean logado = false;//se n estiver logado nao da pra ver os jogos
        do {
            System.out.println("Digite a opção desejada:");
            System.out.println("1 - Cadastre-se");
            System.out.println("2 - Login");
            System.out.println("3 - Fechar");
            if (logado == true) {
                System.out.println("4 - Deslogar");
                System.out.println("5 - Selecionar jogos");
            }
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());
            if (opcao == 1 && !logado) {//se tiver ! vai inverter o valor, exemplo !true=falso 
                System.out.println("Cadastrando conta... ");
                System.out.print("Digite o nome: ");
                String nome = sc.nextLine();
                boolean valido = true;
                String email = "";
                while (valido) {
                    System.out.print("Digite o e-mail: ");
                    email = sc.nextLine();
                    if (!email.contains("@")) {
                        System.out.println("ERRO: Email sem @");
                        System.out.print("Digite o e-mail novamente: ");
                        email = sc.nextLine();
                    } else {
                        valido = false;
                    }

                }

                System.out.print("Digite a senha: ");
                String senha = sc.nextLine();

                Conta c = new Conta(nome);
                c.setEmail(email);
                c.setSenha(senha);

                listaContas.add(c);
                escreveArquivo();
            }
            if (opcao == 2 && !logado) {

                System.out.println("Logando... ");
                System.out.print("Digite o e-mail: ");
                String email = sc.nextLine();

                System.out.print("Digite a senha: ");
                String senha = sc.nextLine();
                boolean login = false;
                for (int i = 0; i < listaContas.size(); i++) {
                    Conta m = listaContas.get(i);
                    if (m.getEmail().equalsIgnoreCase(email) && m.getSenha().equalsIgnoreCase(senha)) {
                        logado = true;
                        login = true;
                    }
                }
                if (!login) {
                    System.out.println("Email ou senha incorretos.");
                }

            }
            if (opcao == 3) {

                System.out.println("Obrigado pela atencao :)");
                return;
            }

            if (opcao == 4 && logado) {//&& so consegue entrar se tiver logada
                System.out.println("Deslogando... ");
                logado = false;
            }
            if (opcao == 5 && logado) {
                System.out.println("Aqui está a lista de jogos que temos em nosso estoque.");
                for (int i = 0; i < listaJogo.size(); i++) {
                    System.out.println(listaJogo.get(i).toString());
                }
                System.out.print("Escolha o número do jogo que você quer comprar:");//procurando joguinho e processo de venda
                int idCompra = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < listaJogo.size(); i++) {
                    Jogo x = listaJogo.get(i);
                    if (x.getId() == idCompra) {
                        System.out.print("Nome: ");
                        System.out.println(x.getNome());
                        System.out.print("Gênero: ");
                        System.out.println(x.getGenero());
                        System.out.print("Classificação Indicativa: ");
                        System.out.println(x.getClassificacaoIndicativa());
                        System.out.print("Sinopse: ");
                        System.out.println(x.getSinopse());
                        System.out.print("Preço: ");
                        System.out.println("R$ " + x.getPreco());
                        System.out.println("Quer comprar algum de nossos jogo? (s/n)");
                        String str = sc.nextLine();
                        if (str.contains("s")) {
                            System.out.println("Obrigado pela preferência!");

                        } else if (str.contains("n")) {
                            System.out.println("Que pena, espero que goste na próxima!");

                        }

                    }
                }

            }
            if (opcao == 2810) {
                System.out.println("Esta é uma opção secreta reservada apenas para fuincionários.");
                System.out.println("Se você for um cliente, escreva sair.");
                System.out.println("Caso trabalhe aqui escreva continuar.");
                String f = sc.nextLine();
                if (f.contains("continuar")) {
                    System.out.println("Adicionando o Jogo...");
                    System.out.print("Digite o Nome do Jogo: ");
                    String nomeJogo = sc.nextLine();

                    System.out.print("Digite o Gênero: ");
                    String genero = sc.nextLine();

                    System.out.print("Digite a Classificação Indicativa: ");
                    String classificacaoIndicativa = sc.nextLine();

                    System.out.print("Digite a Sinopse: ");
                    String sinopse = sc.nextLine();

                    System.out.print("Digite o Preço: ");
                    double preco = Double.parseDouble(sc.nextLine());//double pra ter , no preco

                    Jogo j = new Jogo(nomeJogo);
                    j.setGenero(genero);
                    j.setClassificacaoIndicativa(classificacaoIndicativa);
                    j.setSinopse(sinopse);
                    j.setPreco(preco);

                    listaJogo.add(j);
                    escreveArquivoJogo();

                } else if (f.contains("sair")) {
                    return;

                }
            }
              if (opcao == 2819) {
                  System.out.println("Cuidado ao prosseguir,esta opcao apagara jogos salvos.");
                  
                  for (int i = 0; i < listaJogo.size(); i++) {
                      System.out.println( listaJogo.get(i).toString());
                      
                  }
                  System.out.println("Qual o numero de id do jogo que voce quer apagar?");
                  int idescrito;
                  try{// testar 1x se for certo continua, se n fuincionar ativa o catch, id=-1 e isso quer dizer q ele vai voltar pro menu
                      
                  idescrito = Integer.parseInt(sc.nextLine());
                  }
                  catch(Exception e){
                   idescrito = -1;
                   }
                  for (int i = 0; i < listaJogo.size(); i++) {
                   Jogo temp = listaJogo.get(i);
                      if (temp.getId()== idescrito) {//pegar jogo temporario
                          System.out.println(temp.toString());
                          System.out.println("Voce tem certeza que quer apagar?");
                          String sn = sc.nextLine();
                          if (sn.equalsIgnoreCase("sim")) {
                              listaJogo.remove(temp);
                              //Jogo.addTotalJogosApagados();
                              escreveArquivoJogo();
                              System.out.println("Jogo removido com sucesso.");
                              
                          }
                          else{
                              System.out.println("Procedimento cancelado");
                              
                              
                          }
                          
                      }
                  }
            }
        } while (true);

    }

}




//adicionar quantos jogos eu quiser 
//no maximo apagar 1 jogo se nao da probleminha
//nao consegui arrumar problema de nao repetir id, o erro e mais eu apago mais repete id










//do e while executar sem parar

//&& retorna true quando os dois atributos comparados forem verdadeiros

//return sair do processo atual, ou do que esta acontecendo
