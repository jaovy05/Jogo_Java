package app;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import classes.Jogador;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PodioController implements Initializable{
    private static List<Jogador> jogadores;
    private static int vencedores; 

    @FXML
    private Label campeao;

    @FXML
    private AnchorPane podio;

    @FXML
    private ImageView trofeu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timeline = new Timeline();
        for(int i = 0; i < 3; i++) {
            /*final int index = i;
         //   final int x = i * 165;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i+1), new EventHandler<ActionEvent>() {   
                @Override
                public void handle(ActionEvent event) {
                    Jogador jogador = jogadores.get(index);

                    Label nomeJogador = new Label(jogador.getNome()); 
                    ImageView fotoJogador = new ImageView(jogador.getFoto());
                    Label pontosJogador = new Label(String.valueOf(jogador.getPontos()));
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DODGERBLUE);
                    fotoJogador.setFitWidth(127);
                    fotoJogador.setFitHeight(127);

                    if(index == 0){
                        rectangle.setHeight(233);
                        rectangle.setLayoutX(165);
                    } else if (index == 1) {
                        rectangle.setHeight(167);
                         rectangle.setLayoutX(330);
                    } else {
                        rectangle.setHeight(66);
                        rectangle.setLayoutX(0);
                    }
                    
                    rectangle.setWidth(165);
                    
                    podio.getChildren().add(rectangle);
                    podio.getChildren().add(pontosJogador);
                    podio.getChildren().add(fotoJogador);
                    podio.getChildren().add(nomeJogador);
                    nomeJogador.setPrefWidth(165);
                    pontosJogador.setPrefWidth(165);
                    nomeJogador.setContentDisplay(ContentDisplay.CENTER);
                    pontosJogador.setContentDisplay(ContentDisplay.CENTER);
                    AnchorPane.setBottomAnchor(rectangle, 0.0);
                    
                    nomeJogador.setLayoutY(376 - rectangle.getHeight());
                    nomeJogador.setLayoutX(rectangle.getLayoutX());
                    pontosJogador.setLayoutY(nomeJogador.getLayoutY() + 32);
                    pontosJogador.setLayoutX(rectangle.getLayoutX());
                    fotoJogador.setLayoutY(nomeJogador.getLayoutY() - 126);
                    fotoJogador.setLayoutX(rectangle.getLayoutX() + 19);

                }
            });
            timeline.getKeyFrames().add(keyFrame);*/
            Jogador jogador = jogadores.get(i);

            Label nomeJogador = (Label) podio.getChildren().get(i + 3);
            ImageView fotoJogador = (ImageView) podio.getChildren().get(i + 6);
            Label pontosJogador = (Label) podio.getChildren().get(i + 9);

            fotoJogador.setImage(jogador.getFoto());
            nomeJogador.setText(jogador.getNome());
            pontosJogador.setText(String.valueOf(jogador.getPontos()));

            
            if(i < vencedores && i != 0){
                campeao.setText("Campeões");
                Rectangle rectangle = (Rectangle) podio.getChildren().get(i);
                rectangle.setHeight(233);
                fotoJogador.setLayoutY(14);
                nomeJogador.setLayoutY(138);
                pontosJogador.setLayoutY(170);

                ImageView imageView = new ImageView(trofeu.getImage());
                podio.getChildren().add(imageView);
                imageView.setStyle("-fx-rotate: 10;");
                imageView.setFitHeight(95);
                imageView.setFitWidth(98);
                imageView.setLayoutX(fotoJogador.getLayoutX() + 71);
                imageView.setLayoutY(fotoJogador.getLayoutY() + 35);
            }
        }
        timeline.play();
    }

    @FXML
    void detalhesPontos(ActionEvent event) throws IOException {
        zerar();
        PontuacaoController.setJogadoresList(jogadores);
        PontuacaoController.setVencedores(vencedores);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pontuacaoLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(tela);
        stage.show();
        
        stage.show();
    }

    @FXML
    void AbrirMenu(ActionEvent event) throws IOException {
        zerar();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(tela);
        stage.show();
        
        stage.show();
    }

    @FXML
    void novoJogo(ActionEvent event) throws IOException {
        zerar();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selecaoLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(tela);
        stage.show();
    }

    public static void setJogadores(List<Jogador> jogador) {
        jogadores = jogador;
    }

    public static void setVencedores(int ganhadores) {
       vencedores = ganhadores;
    }

    private void zerar(){
        Image vazio = new Image(getClass().getResourceAsStream("../img/iconUser.png"));
        for(int i = 0; i < 6; i++){
            SelecaoController.imagens.set(i, vazio);
            SelecaoController.nomes.set(i, "");
    }}
}
