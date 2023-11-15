import java.util.*;

public class Tabuleiro {
    private Baralho baralho = new Baralho();
    private List<Jogador> jogadores = new ArrayList<>();
    private List<LinkedList<Carta>> tabuleiro;

    public Tabuleiro(int qtdJogadores){
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < qtdJogadores; i++){ 
            System.out.println("Digite seu nome: ");
            String nome = sc.nextLine();
            jogadores.add(new Jogador(nome, baralho));
        }

        tabuleiro = new ArrayList<>(5);
        //usa deck para colunas
        for(int i = 0; i < 5; i++) {
            try{
                tabuleiro.add(new LinkedList<>());
                tabuleiro.get(i).addLast(baralho.comprar());
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
    
    public void printTabuleiro(){
        //Printar a situação do tabuleiro
        for (int i = 0; i < 5; i++) {
            for(Carta c : tabuleiro.get(i)) System.out.print(c.toString());
            System.out.println();
            
        }
    }

    public void rodada(){
        for(Jogador j : jogadores){
            boolean naoEscolheu = true;
            while (naoEscolheu){
                try {
                    j.setCartaJogada((escolherCarta(j)));
                    naoEscolheu = false;
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        } 
        Collections.sort(jogadores, new Sort());
        for(Jogador j : jogadores){
            Integer indexAntecessor = antecessor(j.getCartaJogada());
            if(indexAntecessor == -1)
              j.comprarLinha(tabuleiro.get(posMaiorElemento()));
            else {
                if(tabuleiro.get(indexAntecessor).size() ==  5)
                    j.comprarLinha(tabuleiro.get(indexAntecessor));
                else tabuleiro.get(indexAntecessor).addLast(j.getCartaJogada());
            }
        }
    }

    public void printJogadores(){
        //Printar a situação de cada jogador
        for(Jogador j: jogadores){
            System.out.println("Jogador: " + j.getNome());
            System.out.println("Pontos: " + j.pontos());
        } 
    }

    public Carta escolherCarta(Jogador jogador) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha sua carta " + jogador.getNome() + ":");
        for (int i = 0; i < 5; i++) {
            for(Carta c : jogador.getMaoJogador()) System.out.print(c.toString());
            System.out.println();
        }
        Integer numero = sc.nextInt();
        sc.close();
        for(Carta c : jogador.getMaoJogador()) 
            if(c.getNumero() == numero) {
                jogador.getMaoJogador().remove(c);
                return c;
            }

        throw new Exception("Escolha uma carta válida");
    }

    public Integer antecessor(Carta cartaJogada){
        Integer antecessor = 0, pos = -1, ultimaCartadaLinha;
        for(int i = 0; i < 5; i++){
            ultimaCartadaLinha = tabuleiro.get(i).getLast().getNumero();
            if(ultimaCartadaLinha < cartaJogada.getNumero() && ultimaCartadaLinha > antecessor){
                    antecessor = ultimaCartadaLinha;
                    pos = i;
            }
        }
        return pos;
    }

    public Integer posMaiorElemento(){
        throw new RuntimeException();
    }
}
