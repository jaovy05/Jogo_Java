import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Carta> baralho;

    public Baralho() {
        this.baralho = criaMonte();
        embaralha();
    }

    private List<Carta> criaMonte() {
        List<Carta> novoBaralho = new ArrayList<>();
        for (int i = 1; i <= 109; i++) {
            novoBaralho.add(new Carta(Integer.toString(i)));
        }
        return novoBaralho;
    }

    private void embaralha() {
        Collections.shuffle(baralho);
    }

    public Carta comprar() throws Exception{
        throw new Exception("Falta implementar o método");
    }
}