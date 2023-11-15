import java.util.HashSet;
import java.util.Set;

public class Carta {
    private Integer numero;
    private Integer pontos;

    public Carta(int numero){
        pontos = 1;
        if(numero % 10 == 5) pontos ++;
        else if(numero % 10 == 0) pontos += 2;
        numeroRepetido(numero);

        this.numero = numero;
    }

    private void numeroRepetido(int numero){
        Set<Integer> conjuntoNumeros = new HashSet<>();
        //Se não for possivel adicionar o int, é porque tem um elemento repetido no conjunto
        for(int i = 1; i <= numero; i *= 10)
            if(!conjuntoNumeros.add( (numero / i) % 10 )) pontos += 4;
    }

    @Override
    public String toString(){
        return numero + " ";
    }

    public Integer getPontos() {
        return pontos;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}
