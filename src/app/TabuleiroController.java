package app;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Carta;
import classes.Jogador;
import classes.Tabuleiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TabuleiroController implements Initializable {

    private Tabuleiro tabuleiro;

    @FXML
    private VBox tabuleiroEsqueleto;

    @FXML
    private AnchorPane mao;
    
    @FXML
    private HBox linha0;

    @FXML
    private HBox linha1;

    @FXML
    private HBox linha2;

    @FXML
    private HBox linha3;
    
    @FXML
    private HBox linha4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabuleiro = new Tabuleiro(SelecaoController.getNomes(), tabuleiroEsqueleto);
        tabuleiro.mostrarMao(tabuleiro.jogadores.get(0), mao);
    
    }

    @FXML
    void JogarRodada(ActionEvent event) {
        //tabuleiro.rodada(tabuleiroEsqueleto, mao);
        Jogador jogador = tabuleiro.jogadores.get(0);
        
    }
}
