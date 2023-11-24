import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Baralho {
    private Stack<Carta> baralho;

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

    public void comprar(LinkedList<Carta> maoJogador, int quantidade) { //chamar o método na classe Jogador
        for (int i = 0; i < quantidade; i++) {
            if (!baralho.isEmpty()) {
                Carta carta = baralho.pop();
                maoJogador.add(carta);
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(baralho);
    }
}
