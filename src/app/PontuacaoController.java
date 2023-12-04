package app;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import classes.Jogador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class PontuacaoController implements Initializable{

    private static List<Jogador> jogadoresList;
    private static int vencedores; 

    @FXML
    private VBox jogadores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int tamanhoLista = jogadores.getChildren().size();
        System.out.println(tamanhoLista);
        int pos = 1;
        for(int i = 0; i < 6; i++){ 
            AnchorPane painel = (AnchorPane) jogadores.getChildren().get(i);

            if(i < jogadoresList.size()){
                if(vencedores <= i){
                    pos++;
                }
                
                Label lugar = (Label) painel.getChildren().get(0);
                Label nome = (Label) painel.getChildren().get(1);
                Label pontos = (Label) painel.getChildren().get(2);
                Pane maoMorta = (Pane) painel.getChildren().get(3);         

                Jogador jogador = jogadoresList.get(i);
                lugar.setText(String.valueOf(pos)+"°");
                nome.setText(jogador.getNome());
                pontos.setText(String.valueOf(jogador.getPontos()));

                for(int c = 0; c < maoMorta.getChildren().size(); c++){
                    ImageView carta = (ImageView) maoMorta.getChildren().get(c);
                    if(c < jogador.getMaoMorta().size()){
                        Image image = new Image(getClass().getResourceAsStream(jogador.getMaoMorta().get(c).toString()));
                        carta.setImage(image);
                    }  else {
                        carta.setVisible(false);
                    }
                }}
            else {
                painel.setVisible(false);
            }
        }
    }

    @FXML
    void destacar(MouseEvent event) { 
        ImageView carta = (ImageView) event.getSource();
        ajusteTamanho(carta, 110, 62, -15);
        carta.toFront(); 
    }

    @FXML
    void ocultar(MouseEvent event) {
        ImageView carta = (ImageView) event.getSource();
        ajusteTamanho(carta, 62, 51, 0);
        organizar();
    }



    private void ajusteTamanho(ImageView carta, double altura, double largura, double y){
        carta.setFitHeight(altura);
        carta.setFitWidth(largura);
        carta.setY(y);
    }

    private void organizar(){

    }

    public static void setJogadoresList(List<Jogador> jogadoresList) {
        PontuacaoController.jogadoresList = jogadoresList;
    }

    public static void setVencedores(int vencedores) {
        PontuacaoController.vencedores = vencedores;
    }
}
