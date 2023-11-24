package classes;

import java.util.*;

public class Jogador {
    private String nome;
    private List<Carta> maoJogador;
    //private List<Carta> maoMorta -- para calcular os pontos das cartas coletadas pelo jogador
    private Carta cartaJogada;
    private int pontos;

    public Jogador(String nome, Baralho baralho) {
        this.nome = nome;
        this.maoJogador = new ArrayList<>();
        this.pontos = 0;

        for (int i = 0; i < 12; i++) {
            Carta carta = baralho.drawCard();
            if (carta != null) {
                maoJogador.add(carta);
            }
        }
    }

    /*public void mostrarCarta() {
        System.out.println("Mão do jogador " + nome + ":");
        for (Carta carta : maoJogador) {
            System.out.println("Carta: " + carta.getNumero());
        }
    }*/

    public void comprarLinha(LinkedList<Carta> linha) { //implementar esse método Valéria
        
    }

    public int pontos() { //usar a maoMorta do jogador para calcular os pontos
        int soma = 0;
        for (Carta carta : maoJogador) {
            int valorCarta = carta.getNumero();
            if (valorCarta % 10 == 5) {
                soma += 1;
            } else if (valorCarta % 10 == 0) {
                soma += 2;
            } else if (temDigitosRepetidos(valorCarta)) {
                soma += 4;
            } else {
                soma += 1;
            }
        }
        return soma;
    }

    private boolean temDigitosRepetidos(int numero) {
        String numeroStr = String.valueOf(numero);
        return numeroStr.length() == 2 && numeroStr.charAt(0) == numeroStr.charAt(1);
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
}
