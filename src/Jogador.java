import java.util.List;

public class Jogador {
    private String nome;
    private List<Carta> maoJogador;
    private Carta cartaJogada;

    public Jogador(String nome) {
        this.nome = nome;
        this.maoJogador = maoJogador;
    }

    public void mostrarCarta(){

    }

    public int pontos(){
        int soma;
        return soma;
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

    public void setMaoJogador(List<Carta> maoJogador) {
        this.maoJogador = maoJogador;
    }
    
    public Carta getCartaJogada() {
        return cartaJogada;
    }

    public void setCartaJogada(Carta cartaJogada) {
        this.cartaJogada = cartaJogada;
    }
}
