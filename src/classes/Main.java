package classes;

public class Main {
    public static void main(String[] args) throws Exception {
        Tabuleiro tabuleiro = new Tabuleiro(3);
        for(int i = 0; i < 12; i++){
            tabuleiro.rodada();
        }
    }
}