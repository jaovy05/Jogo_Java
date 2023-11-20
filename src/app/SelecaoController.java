package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Icon;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelecaoController implements Initializable{

    @FXML
    private ImageView Icon;

    @FXML
    private TextField Nome;
    
    public static Image Image1 = new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Icon.setImage(Image1);
    }
        

    @FXML
    void escolherIcon(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OpcoesLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(tela);
        stage.show();
    }

}
