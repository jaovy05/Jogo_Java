package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import classes.Jogador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PodioController implements Initializable{

    private static List<Jogador> jogadores; 

    @FXML
    private AnchorPane podio;

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
        }
    }

    public static void setJogadores(List<Jogador> jogador) {
        jogadores = jogador;
    }
}
