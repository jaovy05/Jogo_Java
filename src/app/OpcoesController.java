package app;

import javax.swing.Icon;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OpcoesController {

    @FXML
    private ImageView op1;

    @FXML
    void escolheu1(MouseEvent event) throws IOException{
        Image image = new Image(getClass().getResourceAsStream("../img/Icon1.png"));
        SelecaoController.Image1 = image;
        abrirSelecao(event);
    }

    @FXML
    void escolheu2(MouseEvent event) {

    }

    @FXML
    void escolheu3(MouseEvent event) {

    }

    @FXML
    void escolheu4(MouseEvent event) {

    }

    private void abrirSelecao(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selecaoLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(tela);
        stage.show();
    }
}
