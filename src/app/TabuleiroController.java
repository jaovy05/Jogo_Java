package app;

import java.net.URL;
import java.util.ResourceBundle;
import classes.Jogador;
import classes.Tabuleiro;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;


public class TabuleiroController implements Initializable {

    private Tabuleiro tabuleiro;

    @FXML
    private VBox tabuleiroEsqueleto;

    @FXML
    private AnchorPane mao;

    @FXML
    private Pane perfil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabuleiro = new Tabuleiro(SelecaoController.nomes, tabuleiroEsqueleto, SelecaoController.imagens);
        tabuleiro.mostrarMao(tabuleiro.jogadores.get(0), mao);
        tabuleiro.printJogador(tabuleiro.jogadores.get(0), perfil);
    }

    @FXML
    void JogarRodada(ActionEvent event) {
        //tabuleiro.rodada(tabuleiroEsqueleto, mao);
        Jogador jogador = tabuleiro.jogadores.get(0);
        
    }
    
    @FXML
    void destacar(MouseEvent event) {
        ImageView cartaSelecionada = (ImageView) event.getSource();
        ajusteTamanho(cartaSelecionada, 135, 93, -10);
        cartaSelecionada.toFront();
    }

    @FXML
    void ocultar(MouseEvent event) {
        ImageView cartaSelecionada = (ImageView) event.getSource();

        ajusteTamanho(cartaSelecionada, 120, 83, 0);
        // Itera sobre os filhos do contêiner
        cartaSelecionada.toBack();
    }

    private void ajusteTamanho(ImageView carta, double altura, double largura, double y){
        carta.setFitHeight(altura);
        carta.setFitWidth(largura);
        AnchorPane.setTopAnchor(carta, y);
    }
}