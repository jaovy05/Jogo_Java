public class Main {
    public static void main(String[] args) throws Exception {
        Baralho baralho = new Baralho();
        for(Carta c: baralho.getBaralho()){
            System.out.println(c);
        }

    }
}