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
            jogadores.add(new Jogador(nome));}
        tabuleiro = new ArrayList<>(5);
        //usa deck para colunas
        for(int i = 0; i < 5; i++) {
            tabuleiro.add(new LinkedList<>());
            try{
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
        String numero = sc.next();
        sc.close();
        for(Carta c : jogador.getMaoJogador()) 
            if(c.getNumero().equals(numero)) {
                jogador.getMaoJogador().remove(c);
                return c;
            }

        throw new Exception("Escolha uma carta válida");
    }
}
