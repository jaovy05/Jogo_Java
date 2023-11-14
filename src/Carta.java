import java.util.HashSet;
import java.util.Set;

public class Carta {
    private String numero;
    private int pontos;

    public Carta(String numero){
        pontos = 1;
        if(Integer.parseInt(numero) % 10 == 5) pontos ++;
        else if(Integer.parseInt(numero) % 10 == 0) pontos += 2;
        numeroRepetido(numero);

        this.numero = numero;
    }

    private void numeroRepetido(String numero){
        Set<Character> conjuntoNumeros = new HashSet<>();
        //Se não for possivel adicionar o char, é porque tem um elemento repetido no conjunto
        for(char c : numero.toCharArray())
            if(!conjuntoNumeros.add(c)) pontos += 4;
    }

    @Override
    public String toString(){
        return numero + " ";
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
