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

public class TabuleiroController implements Initializable{

    
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
       // Tabuleiro tabuleiro = new Tabuleiro(3);
        Carta c = new Carta(8);
      //  Image image = new Image("../img/"+c.toString());
        //setImageView(linha0, image, 0);
        

    }
    
    private void setImageView(HBox linha, Image image, int index){
        if(linha.getChildren().size() > 0){
            ImageView novaImagem = (ImageView) linha.getChildren().get(index);
            novaImagem.setImage(image);
        }
    }
}
