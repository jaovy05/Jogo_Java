import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Cartas> baralho;

    public Baralho() {
        this.baralho = criaMonte();
        embaralha();
    }

    private List<Cartas> criaMonte() {
        List<Cartas> novoBaralho = new ArrayList<>();
        for (int i = 1; i <= 109; i++) {
            novoBaralho.add(new Cartas(i));
        }
        return novoBaralho;
    }

    private void embaralha() {
        Collections.shuffle(baralho);
    }
}