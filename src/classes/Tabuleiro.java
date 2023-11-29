package classes;

import java.util.*;

import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Tabuleiro {
    private Baralho baralho = new Baralho();
    private List<Jogador> jogadores = new ArrayList<>();
    private List<LinkedList<Carta>> tabuleiro;
    Scanner sc = new Scanner(System.in);

    public Tabuleiro(List<String> nomes, VBox tabuleiroVBox, List<Image> fotos){
        
        
        //for para adicionar os jogadores informados no parâmetro do construtor
        for(int i = 0; i < 6; i++)
            if(nomes.get(i) != "") jogadores.add(new Jogador(nomes.get(i), baralho, fotos.get(i)));
        
        //Arraylist para as linhas
        tabuleiro = new ArrayList<>(5);
        //usa deck para colunas
        for(int i = 0; i < 5; i++) {
            try{
                tabuleiro.add(new LinkedList<>());
                //adiciona a primeira carta das linhas
                tabuleiro.get(i).addLast(baralho.comprar());
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        //mostra a situação inicial do tabuleiro
        printTabuleiro(tabuleiroVBox);
 
    }
    
    public void printTabuleiro(VBox tabuleiroVBox){
        //Printar a situação do tabuleiro
        
        System.out.println();
        for (int l = 0; l < 5; l++) {
            HBox linha = (HBox) tabuleiroVBox.getChildren().get(l);
            for(int c = 0; c < 5; c++) {
                ImageView imageView = (ImageView) linha.getChildren().get(c);
                Image image;
                if(c < tabuleiro.get(l).size()){
                    image = new Image(getClass().getResourceAsStream(tabuleiro.get(l).get(c).toString()));
                   // System.out.print(tabuleiro.get(l).get(c));
                } else {
                    image = new Image(getClass().getResourceAsStream("../img/CasaTabuleiro.png"));
                }
                imageView.setImage(image);
            }
           // System.out.println();
        }
    }

    public void rodada(VBox tabuleiroVBox, AnchorPane cartasJodadas){
        //ordena os jogodares ordem crescente
        Collections.sort(jogadores, new Sort());
        for(int i = 0; i < jogadores.size(); i++){
            ImageView cartaJogada = (ImageView) cartasJodadas.getChildren().get(i);
            Label nome = (Label) cartasJodadas.getChildren().get(i + 6);
            Jogador jogador = jogadores.get(i);
            cartaJogada.setImage(new Image(getClass().getResourceAsStream(jogador.getCartaJogada().toString())));
            nome.setText(jogador.getNome());
        }
        
        for(Jogador j : jogadores){
            //pega o índice do antecessor mais próxima
            Integer indexAntecessor = antecessor(j.getCartaJogada());
            if(indexAntecessor == -1){
                //se não tiver antecessor, compra a linha com maior elemento
                int posMaior = posMaiorElemento();          
                j.comprarLinha(tabuleiro.get(posMaior));
                //adiciona a carta na linha em questão
                tabuleiro.get(posMaior).addLast(j.getCartaJogada());
            } else {
                //se a linha tiver cheia, compra a mesma
                if(tabuleiro.get(indexAntecessor).size() ==  5)
                    j.comprarLinha(tabuleiro.get(indexAntecessor));
                //adiciona a carta na linha do antecessor
                tabuleiro.get(indexAntecessor).addLast(j.getCartaJogada());
            }
            j.pontos();
        }
        //mostra a situação do tabuleiro
        printTabuleiro(tabuleiroVBox);
        //e dos jogadores
        printJogadores();
    }

    public void printJogadores(){
        //Printar a situação de cada jogador
        System.out.println();
        for(Jogador j: jogadores){
            System.out.println("Jogador: " + j.getNome());
            System.out.println("Pontos: " + j.getPontos());
        } 
    }

     public void printJogador(Jogador jogador, Pane perfil){
        //Printar a situação do jogador
        ImageView imageView = (ImageView) perfil.getChildren().get(0);
        MenuBar nome = (MenuBar) perfil.getChildren().get(1);
        Label pontos = (Label) perfil.getChildren().get(2);

        imageView.setImage(jogador.getFoto());
        nome.getMenus().get(0).setText(jogador.getNome());
        pontos.setText("Pontos : " + jogador.getPontos());
    }

    public Carta escolherCarta(Jogador jogador, AnchorPane mao){

        //chama o jogador pelo nome
        System.out.println("Escolha sua carta " + jogador.getNome() + ":");
        //printa a mão do jogador
        for(int i = 0; i < jogador.getMaoJogador().size(); i++) {
            Image image = new Image(getClass().getResourceAsStream(jogador.getMaoJogador().get(i).toString()));
            ImageView imageView = (ImageView) mao.getChildren().get(0);
            imageView.setImage(image);
        }
        System.out.println();

        try {
            Integer numero = sc.nextInt();
            for(Carta c : jogador.getMaoJogador()) 
            //verifica se o número informado está na mão
                if(c.getNumero() == numero) {
                    //se tiver, remove da mão e retorna a carta
                    jogador.getMaoJogador().remove(c);
                    return c;
                }
        } catch (Exception e) {
            sc.nextLine();
            throw new RuntimeException("Escolha uma carta válida");
        }
        //se não encontrar retorna exception
        throw new RuntimeException("Escolha uma carta válida");
    }
    

    public Integer antecessor(Carta cartaJogada){
        Integer antecessor = 0, pos = -1, ultimaCartadaLinha;
        for(int i = 0; i < 5; i++){
            //pega a última Carta da Linha
            ultimaCartadaLinha = tabuleiro.get(i).getLast().getNumero();
            //se a última carta for menor que a carta jogada é maior que o antecessor, entra no if
            if(ultimaCartadaLinha < cartaJogada.getNumero() && ultimaCartadaLinha > antecessor){
                    //atualiza o antecessor
                    antecessor = ultimaCartadaLinha;
                    //pega a posição do antecessor
                    pos = i;
            }
        }
        //retorna a pos
        return pos;
    }

    public Integer posMaiorElemento(){
        Integer pos = 0, maior = 0;

        for(int i = 0; i < 5; i++){
            if(maior < tabuleiro.get(i).getLast().getNumero()){
                //atuliza o maior e a posição
                maior = tabuleiro.get(i).getLast().getNumero();
                pos = i;
            }
        }
        return pos;
    }

    public void venceu(){
        Jogador ganhou = jogadores.get(0);
        for(Jogador j : jogadores) 
            if(j.getPontos() < ganhou.getPontos())
                ganhou = j;
        
        System.out.println("Campeão: " + ganhou.getNome());
        for(Carta c : ganhou.getMaoMorta())
            System.out.print(c);
        System.out.println();
        for(Jogador j : jogadores)
            if(j != ganhou){
                System.out.println("Nome: " + j.getNome());
                for(Carta c : j.getMaoMorta())
                    System.out.print(c);
                System.out.println();
            }
        
    }

    public void mostrarMao(Jogador jogador, AnchorPane mao){
        
        for(int i = 0; i < 12; i++) { 
            
            ImageView imageView = (ImageView) mao.getChildren().get(i);
            Image image;
            if(i < jogador.getMaoJogador().size()){
                image = new Image(getClass().getResourceAsStream(jogador.getMaoJogador().get(i).toString()));
                System.out.print(jogador.getMaoJogador().get(i) + " ");
            } else {
                image = new Image(getClass().getResourceAsStream("../img/verso.png"));
            }
            imageView.setImage(image);
        }
        System.out.println();
    }
    
    public List<Jogador> getJogadores() {
        return jogadores;
    }
}
