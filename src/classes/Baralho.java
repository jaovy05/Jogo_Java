package classes;

import java.util.*;

public class Baralho {
    private Stack<Carta> baralho;

    public Stack<Carta> getBaralho() {
        return baralho;
    }

    public Baralho() {
        this.baralho = new Stack<>();
        criarBaralho();
        embaralhar();
    }

    private void criarBaralho() {
        for (int i = 1; i <= 109; i++) {
            baralho.push(new Carta(i));
        }
    }

    public Carta comprar() { //chamar o método na classe Jogador
        return baralho.pop();
    }

    public void comprar(List<Carta> maoMorta, int quantidade) { //chamar o método na classe Jogador
        for (int i = 0; i < quantidade; i++) {
            if (!baralho.isEmpty()) {
                Carta carta = baralho.pop();
                maoMorta.add(carta);
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(baralho);
    }
}
