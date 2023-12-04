package classes;

import java.util.*;
import javafx.scene.image.Image;

public class Jogador implements Comparable<Jogador> {
    private String nome;
    private List<Carta> maoJogador;
    private List<Carta> maoMorta;
    private Carta cartaJogada;
    private int pontos;   
    private Image foto;
    
    public Jogador(String nome, Baralho baralho, Image foto) {
        this.nome = nome;
        this.maoJogador = new ArrayList<>();
        this.maoMorta= new ArrayList<>();
        this.pontos = 0;
        this.foto = foto;

        baralho.comprar(maoJogador, 12);
    }

    public void comprarLinha(LinkedList<Carta> linha) { 
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
       // for(Carta c : maoJogador)
          //  if(c.getNumero() == cartaJogada.getNumero()) {
             //   maoJogador.remove(c);
           // }
        maoJogador.remove(cartaJogada);
        this.cartaJogada = cartaJogada;
    }
    
    public int getPontos() {
        return pontos;
    } 
    
    public List<Carta> getMaoMorta() {
        return maoMorta;
    }
    
    public Image getFoto() {
        return foto;
    }

    @Override
    public int compareTo(Jogador j) {
        return this.pontos - j.pontos;
    }
}
