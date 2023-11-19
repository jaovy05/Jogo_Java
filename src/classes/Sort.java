package classes;

import java.util.Comparator;

public class Sort implements Comparator<Jogador>{
    @Override
    public int compare(Jogador a, Jogador b) {
        return a.getCartaJogada().getNumero() - b.getCartaJogada().getNumero();
    }
    
}
