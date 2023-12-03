package classes;

import java.io.IOException;
import java.util.*;

import app.PodioController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Tabuleiro {
    private Baralho baralho = new Baralho();
    private List<Jogador> jogadores = new ArrayList<>();
    private List<LinkedList<Carta>> tabuleiro;
    Scanner sc = new Scanner(System.in);

    public Tabuleiro(List<String> nomes, VBox tabuleiroVBox, List<Image> fotos){
        
        
        //for para adicionar os jogadores informados no parâmetro do construtor
        for(int i = 0; i < 6; i++)
            if(nomes.get(i) != "") jogadores.add(new Jogador(nomes.get(i), baralho, fotos.get(i)));
        
        //Arraylist para as linhas
        tabuleiro = new ArrayList<>(5);
        //usa deck para colunas
        for(int i = 0; i < 5; i++) {
            try{
                tabuleiro.add(new LinkedList<>());
                //adiciona a primeira carta das linhas
                tabuleiro.get(i).addLast(baralho.comprar());
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        //mostra a situação inicial do tabuleiro
        printTabuleiro(tabuleiroVBox);
        
    }
    
    public void printTabuleiro(VBox tabuleiroVBox){
        //Printar a situação do tabuleiro
        for (int l = 0; l < 5; l++) {
            HBox linha = (HBox) tabuleiroVBox.getChildren().get(l);
            for(int c = 0; c < 5; c++) {
                ImageView imageView = (ImageView) linha.getChildren().get(c);
                Image image;
                if(c < tabuleiro.get(l).size()){
                    image = new Image(getClass().getResourceAsStream(tabuleiro.get(l).get(c).toString()));

                } else {
                    image = new Image(getClass().getResourceAsStream("../img/CasaTabuleiro.png"));
                }
                imageView.setImage(image);
            }
        }
    }

    public void rodada(VBox tabuleiroVBox, AnchorPane cartasJogadas, AnchorPane jogadorPane)  {
        //ordena os jogodares ordem crescente
        Collections.sort(jogadores, new Sort());
        
        for(int i = 0; i < jogadores.size(); i++){
            ImageView cartaJogada = (ImageView) cartasJogadas.getChildren().get(i);
            Label nome = (Label) cartasJogadas.getChildren().get(i + 6);
            Jogador jogador = jogadores.get(i);
            cartaJogada.setImage(new Image(getClass().getResourceAsStream(jogador.getCartaJogada().toString())));
            nome.setText(jogador.getNome());
            
        }
        cartasJogadas.setVisible(true);
        Timeline timeline = new Timeline();
        
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            final int index = i;
    
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i+1), new EventHandler<ActionEvent>() {   
                @Override
                public void handle(ActionEvent event) { 
                    Node removido =  cartasJogadas.getChildren().get(0);
                    if(removido instanceof Rectangle)
                        cartasJogadas.getChildren().remove(0);
                    
                    Rectangle rectangle = new Rectangle(93, 130, Color.WHITE);
                    rectangle.setStrokeWidth(10);
                    rectangle.setStrokeType(StrokeType.INSIDE);
                    rectangle.setStroke(Color.web("#63b573"));  
                    
                    Integer indexAntecessor = antecessor(j.getCartaJogada());
                    if (indexAntecessor == -1) {
                        int posMaior = posMaiorElemento();
                        j.comprarLinha(tabuleiro.get(posMaior));
                        tabuleiro.get(posMaior).addLast(j.getCartaJogada());   
                        rectangle.setStroke(Color.web("#ab1d2b"));                
                    } else {
                        if (tabuleiro.get(indexAntecessor).size() == 5) {
                            j.comprarLinha(tabuleiro.get(indexAntecessor));
                            rectangle.setStroke(Color.web("#ab1d2b")); 
                        }
                        tabuleiro.get(indexAntecessor).addLast(j.getCartaJogada());
                    }

                    ImageView carta = (ImageView) cartasJogadas.getChildren().get(index);                   
                    cartasJogadas.getChildren().add(rectangle);
                    rectangle.toBack();
                    rectangle.setX(carta.getLayoutX() - 5);
                    rectangle.setY(carta.getLayoutY() - 5); 

                    j.pontos();
                    printTabuleiro(tabuleiroVBox);
                }
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        KeyFrame finalFrame = new KeyFrame(Duration.seconds(jogadores.size() + 1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                cartasJogadas.getChildren().remove(0);
                jogadorPane.setVisible(true);
                cartasJogadas.setVisible(false);
            }
        });
        timeline.getKeyFrames().add(finalFrame);

        timeline.play();
    }

    public void printJogador(Jogador jogador, Pane perfil){
        ImageView imageView = (ImageView) perfil.getChildren().get(0);
        MenuBar nome = (MenuBar) perfil.getChildren().get(1);
        Label pontos = (Label) perfil.getChildren().get(2);

        imageView.setImage(jogador.getFoto());
        nome.getMenus().get(0).setText(jogador.getNome());
        pontos.setText("Pontos : " + jogador.getPontos());
    }

    public Integer antecessor(Carta cartaJogada){
        Integer antecessor = 0, pos = -1, ultimaCartadaLinha;
        for(int i = 0; i < 5; i++){
            //pega a última Carta da Linha
            ultimaCartadaLinha = tabuleiro.get(i).getLast().getNumero();
            //se a última carta for menor que a carta jogada é maior que o antecessor, entra no if
            if(ultimaCartadaLinha < cartaJogada.getNumero() && ultimaCartadaLinha > antecessor){
                    //atualiza o antecessor
                    antecessor = ultimaCartadaLinha;
                    //pega a posição do antecessor
                    pos = i;
            }
        }
        //retorna a pos
        return pos;
    }

    public Integer posMaiorElemento(){
        Integer pos = 0, maior = 0;

        for(int i = 0; i < 5; i++){
            if(maior < tabuleiro.get(i).getLast().getNumero()){
                //atuliza o maior e a posição
                maior = tabuleiro.get(i).getLast().getNumero();
                pos = i;
            }
        }
        return pos;
    }

    public void venceu(ActionEvent event) throws IOException{
        int vencedores = 1;
        Collections.sort(jogadores);

        for(int i = 1; i < jogadores.size() && jogadores.get(i).getPontos() == jogadores.get(i - 1).getPontos(); i++)
            vencedores++;

        PodioController.setVencedores(vencedores);
        PodioController.setJogadores(jogadores);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../app/podioLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(tela);
        stage.show();
    }

    public void mostrarMao(Jogador jogador, AnchorPane mao){
        for(int i = 0; i < 12; i++) { 
            ImageView imageView = (ImageView) mao.getChildren().get(i);
            Image image;
            if(i < jogador.getMaoJogador().size()){
                image = new Image(getClass().getResourceAsStream(jogador.getMaoJogador().get(i).toString()));
            } else {
                image = new Image(getClass().getResourceAsStream("../img/verso.png"));
            }
            imageView.setImage(image);
        }
    }
    
    public List<Jogador> getJogadores() {
        return jogadores;
    }
}
