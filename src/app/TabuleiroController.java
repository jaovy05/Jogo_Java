package app;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Carta;
import classes.Tabuleiro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TabuleiroController implements Initializable {

    @FXML
    private VBox tabuleiroEsqueleto;

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
        Tabuleiro tabuleiro = new Tabuleiro(SelecaoController.getNomes());
        Carta c = new Carta(8);
        Image image = new Image(getClass().getResourceAsStream("../img/" + c));
        setImageView(tabuleiroEsqueleto, image, 0, 0); 
    }

    private void setImageView(VBox tabule, Image image, int linha, int coluna) {
        HBox linhaBox = (HBox) tabule.getChildren().get(linha);
        ImageView imageView = (ImageView) linhaBox.getChildren().get(coluna);
        imageView.setImage(image);
    }
}
