package app;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import classes.Jogador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
        for(int i = 0; i < 3; i++) {
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
                System.out.println(podio.getPrefWidth());
            }
        }

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
       System.out.println(ganhadores);
    }

    private void zerar(){
        Image vazio = new Image(getClass().getResourceAsStream("../img/iconUser.png"));
        for(int i = 0; i < 6; i++){
            SelecaoController.imagens.set(i, vazio);
            SelecaoController.nomes.set(i, "");
    }}
}
