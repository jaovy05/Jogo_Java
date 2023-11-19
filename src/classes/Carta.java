package classes;

import java.util.HashSet;
import java.util.Set;

public class Carta {
    private Integer numero;
    private Integer pontos;

    public Carta(int numero){
        //ponto base
        pontos = 1;
        //se terminar com 5, recebe um ponto
        if(numero % 10 == 5) pontos ++;
        //se for divisível por 10, ganha dois pontos
        else if(numero % 10 == 0) pontos += 2;
        //se tiver número repetido, ganha quatro pontos
        numeroRepetido(numero);

        this.numero = numero;
    }

    private void numeroRepetido(int numero){
        //utiliza conjunto, pois conjuntos não aceitam elementos duplicados
        Set<Integer> conjuntoNumeros = new HashSet<>();

        //o for vai repetir até separar todos os digitos
        for(int i = 1; i <= numero; i *= 10)
            //se não for possivel adicionar, entra no if somando pontos
            if(!conjuntoNumeros.add( (numero / i) % 10 )){
                pontos += 4;
                break;
            }
    }

    @Override
    //sobrescrita do método toString para facilitar o print
    public String toString(){
        return numero + " ";
    }

    //daqui para baixo contém somente os Getters e Setters
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
