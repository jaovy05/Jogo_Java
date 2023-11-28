package classes;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Tabuleiro {
    public Baralho baralho = new Baralho();
    public List<Jogador> jogadores = new ArrayList<>();
    public List<LinkedList<Carta>> tabuleiro;
    Scanner sc = new Scanner(System.in);

    public Tabuleiro(List<String> nomes, VBox tabuleiroVBox){
        
        
        //for para adicionar os jogadores informados no parâmetro do construtor
        for(String nome : nomes)
            if(nome != "") jogadores.add(new Jogador(nome, baralho));
        
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
            for(int c = 0; c < tabuleiro.get(l).size(); c++) {
                Image image = new Image(getClass().getResourceAsStream(tabuleiro.get(l).get(c).toString()));
                ImageView imageView = (ImageView) linha.getChildren().get(c);
                imageView.setImage(image);

                System.out.print(tabuleiro.get(l).get(c));
            }
            System.out.println();
        }
    }

    public void rodada(VBox tabuleiroVBox, AnchorPane mao){
        //pega a carta de todos jogadores
        
        for(Jogador j : jogadores){
            j.setCartaJogada((escolherCarta(j, mao)));
        } 
        //ordena os jogodares ordem crescente
        Collections.sort(jogadores, new Sort());
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

     public void printJogador(Jogador jogador){
        //Printar a situação de cada jogador
        System.out.println();
        for(Jogador j: jogadores){
            System.out.println("Jogador: " + j.getNome());
            System.out.println("Pontos: " + j.getPontos());
        } 
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
        for(int i = 0; i < jogador.getMaoJogador().size(); i++) {
            Image image = new Image(getClass().getResourceAsStream(jogador.getMaoJogador().get(i).toString()));
            ImageView imageView = (ImageView) mao.getChildren().get(i);
            imageView.setImage(image);
        }
    }
}
