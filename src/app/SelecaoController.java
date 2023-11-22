package app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelecaoController implements Initializable{

    public static int index;
    public static List<Image> imagens = new ArrayList<>();
    static{
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
    }
    
    @FXML
    private ImageView IconP1, IconP2, IconP3, IconP4, IconP5, IconP6;

    @FXML
    private TextField NomeP1, NomeP2, NomeP3, NomeP4, NomeP5, NomeP6;

    @FXML
    private Label qtdJogadores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IconP1.setImage(imagens.get(0));
        IconP2.setImage(imagens.get(1));
        IconP3.setImage(imagens.get(2));
        IconP4.setImage(imagens.get(3));
        IconP5.setImage(imagens.get(4));
        IconP6.setImage(imagens.get(5));
        qtdJogadores.setText("0/6");
    }
        

    @FXML
    void escolherIcon(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("opcoesLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(tela);
        stage.show();
    }

}