package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import classes.Jogador;
import classes.Tabuleiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class TabuleiroController implements Initializable {
    public static int indexJogador = 0;
    public static int indexCarta = -1;
    private Tabuleiro tabuleiro;

    @FXML
    private VBox tabuleiroEsqueleto;

    @FXML
    private AnchorPane mao, cartasJogadas, jogadorPane;

    @FXML
    private Pane perfil;

    @FXML
    private ImageView c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabuleiro = new Tabuleiro(SelecaoController.nomes, tabuleiroEsqueleto, SelecaoController.imagens);
        tabuleiro.mostrarMao(tabuleiro.getJogadores().get(indexJogador), mao);
        tabuleiro.printJogador(tabuleiro.getJogadores().get(indexJogador), perfil);
    }
    
    @FXML
    void cartaJogada(MouseEvent event)   {
        Jogador jogador = tabuleiro.getJogadores().get(indexJogador);
        if(indexCarta < jogador.getMaoJogador().size()){        
            jogador.setCartaJogada(jogador.getMaoJogador().get(indexCarta));
            if(indexJogador + 1 == tabuleiro.getJogadores().size()){    
                jogadorPane.setVisible(false);         
                tabuleiro.rodada(tabuleiroEsqueleto, cartasJogadas, jogadorPane);
                indexJogador = 0;      
            } else {
                indexJogador++;
            }
            organizar();
            tabuleiro.mostrarMao(tabuleiro.getJogadores().get(indexJogador), mao);
            tabuleiro.printJogador(tabuleiro.getJogadores().get(indexJogador), perfil);
        }
    }
    
    @FXML
    void destacar(MouseEvent event) { 
        ImageView carta = (ImageView) event.getSource();
        indexCarta = mao.getChildren().indexOf(carta);
        ajusteTamanho(carta, 135, 93, -10);
        carta.toFront(); 
    }

    @FXML
    void ocultar(MouseEvent event) {
        ImageView carta = (ImageView) event.getSource();
        ajusteTamanho(carta, 120, 83, 0);
        organizar();
    }

    @FXML
    void vencedor(ActionEvent event) throws IOException {
        tabuleiro.venceu(event);
    }

    private void ajusteTamanho(ImageView carta, double altura, double largura, double y){
        carta.setFitHeight(altura);
        carta.setFitWidth(largura);
        AnchorPane.setTopAnchor(carta, y);
    }

    private void organizar(){
        c0.toFront();
        c1.toFront();
        c2.toFront();
        c3.toFront();
        c4.toFront();
        c5.toFront();
        c6.toFront();
        c7.toFront();
        c8.toFront();
        c9.toFront();
        c10.toFront();
        c11.toFront();
    }
}