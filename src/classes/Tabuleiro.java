package classes;

import java.util.*;

public class Tabuleiro {
    public Baralho baralho = new Baralho();
    public List<Jogador> jogadores = new ArrayList<>();
    public List<LinkedList<Carta>> tabuleiro;
    Scanner sc = new Scanner(System.in);

    public Tabuleiro(int qtdJogadores){
        
        
        //for para adicionar a quantidade de jogadores informada no parâmetro do construtor
        for(int i = 0; i < qtdJogadores; i++){ 
            System.out.println("Digite seu nome: ");
            String nome = sc.nextLine();
            jogadores.add(new Jogador(nome, baralho));
        }
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
        printTabuleiro();
 
    }
    
    public void printTabuleiro(){
        //Printar a situação do tabuleiro
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for(Carta c : tabuleiro.get(i)) System.out.print(c.toString());
            System.out.println();
        }
    }

    public void rodada(){
        //pega a carta de todos jogadores
        for(Jogador j : jogadores){
            boolean naoEscolheu = true;
            //enquanto não escolher o loop continua
            while (naoEscolheu){
                try {
                    //adiciona a carta jogada ao jogador
                    j.setCartaJogada((escolherCarta(j)));
                    naoEscolheu = false;
                } catch(Exception e){
                    //diz para escolher a carta certo
                    System.out.println(e.getMessage());
                    
                }
            }
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
        printTabuleiro();
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

    public Carta escolherCarta(Jogador jogador) throws Exception{

        //chama o jogador pelo nome
        System.out.println("Escolha sua carta " + jogador.getNome() + ":");
        //printa a mão do jogador
        for(Carta c : jogador.getMaoJogador()) System.out.print(c);
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
            throw new Exception("Escolha uma carta válida");
        }
        
        
        
        //se não encontrar retorna exception
        throw new Exception("Escolha uma carta válida");
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
}
