package app;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OpcoesController {

    @FXML
    private ImageView op1, op2, op3, op4, op5, op6, op7, op8, op9, op10, op11, op12, op13, op14, op15, op16;

    @FXML
    void escolheu1(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op1.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu10(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op10.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu11(MouseEvent event) throws IOException {
        SelecaoController.imagens.set(SelecaoController.index, op11.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu12(MouseEvent event) throws IOException {
        SelecaoController.imagens.set(SelecaoController.index, op12.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu13(MouseEvent event) throws IOException {
        SelecaoController.imagens.set(SelecaoController.index, op13.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu14(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op14.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu15(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op15.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu16(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op16.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu2(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op2.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu3(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op3.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu4(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op4.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu5(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op5.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu6(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op6.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu7(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op7.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu8(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op8.getImage());
        abrirSelecao(event);
    }

    @FXML
    void escolheu9(MouseEvent event) throws IOException{
        SelecaoController.imagens.set(SelecaoController.index, op9.getImage());
        abrirSelecao(event);
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