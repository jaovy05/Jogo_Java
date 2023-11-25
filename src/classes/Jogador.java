package classes;

import java.util.*;

public class Jogador {
    private String nome;
    private List<Carta> maoJogador;
    private List<Carta> maoMorta ;

    private Carta cartaJogada;
    private int pontos;   

    public Jogador(String nome, Baralho baralho) {
        this.nome = nome;
        this.maoJogador = new ArrayList<>();
        this.maoMorta= new ArrayList<>();
        this.pontos = 0;

        baralho.comprar(maoJogador, 12);
    }

    /*public void mostrarCarta() {
        System.out.println("Mão do jogador " + nome + ":");
        for (Carta carta : maoJogador) {
            System.out.println("Carta: " + carta.getNumero());
        }
    }*/

    public void comprarLinha(LinkedList<Carta> linha) { //implementar esse método Valéria
        while (!linha.isEmpty()) 
            maoMorta.add(linha.removeLast());
        
    }

    public void pontos() { //usar a maoMorta do jogador para calcular os pontos
        int soma = 0;
        for (Carta carta : maoMorta) {
            soma += carta.getPontos();
        }
        pontos = soma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Carta> getMaoJogador() {
        return maoJogador;
    }

    public Carta getCartaJogada() {
        return cartaJogada;
    }

    public void setCartaJogada(Carta cartaJogada) {
        this.cartaJogada = cartaJogada;
    }
    
    public int getPontos() {
        return pontos;
    } 
    
    public List<Carta> getMaoMorta() {
        return maoMorta;
    }
}
