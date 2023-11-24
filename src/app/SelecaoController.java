package app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelecaoController implements Initializable{
    private int jogadoresvalidos = 0;
    private static List<String> nomes = new ArrayList<>();
    public static int index;
    public static List<Image> imagens = new ArrayList<>();
    static{
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        imagens.add(new Image(SelecaoController.class.getResourceAsStream("../img/IconVazio.png")));
        nomes.add("");
        nomes.add("");
        nomes.add("");
        nomes.add("");
        nomes.add("");
        nomes.add("");
    }
    
    @FXML
    private ImageView IconP0, IconP1, IconP2, IconP3, IconP4, IconP5;

    @FXML
    private TextField NomeP0, NomeP1, NomeP2, NomeP3, NomeP4, NomeP5;

    @FXML
    private Label qtdJogadores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IconP0.setImage(imagens.get(0));
        IconP1.setImage(imagens.get(1));
        IconP2.setImage(imagens.get(2));
        IconP3.setImage(imagens.get(3));
        IconP4.setImage(imagens.get(4));
        IconP5.setImage(imagens.get(5));
        
        NomeP0.setText(nomes.get(0));
        NomeP1.setText(nomes.get(1));
        NomeP2.setText(nomes.get(2));
        NomeP3.setText(nomes.get(3));
        NomeP4.setText(nomes.get(4));
        NomeP5.setText(nomes.get(5));
        
              
        verificarJogadores(null);
    }
    
    @FXML
    void verificarJogadores(KeyEvent event) {
        jogadoresvalidos = 0;
        if(NomeP0.getText() != "") jogadoresvalidos++;
        if(NomeP1.getText() != "") jogadoresvalidos++;
        if(NomeP2.getText() != "") jogadoresvalidos++;
        if(NomeP3.getText() != "") jogadoresvalidos++;
        if(NomeP4.getText() != "") jogadoresvalidos++;
        if(NomeP5.getText() != "") jogadoresvalidos++;
        
        nomes.set(0, NomeP0.getText());
        nomes.set(1, NomeP1.getText());
        nomes.set(2, NomeP2.getText());
        nomes.set(3, NomeP3.getText());
        nomes.set(4, NomeP4.getText());
        nomes.set(5, NomeP5.getText());
        

        qtdJogadores.setText(jogadoresvalidos + "/6");
        if(jogadoresvalidos < 3) qtdJogadores.setStyle("-fx-text-fill: red;");
        else qtdJogadores.setStyle("-fx-text-fill: blue;");
    }

     @FXML
    void iniciarJogo(ActionEvent event) throws IOException {
        if(jogadoresvalidos >= 3){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tabuleiroLayout.fxml"));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(tela);
            stage.show();
        } else {
            JOptionPane.showConfirmDialog(null,"É necessário pelo menos 3 jogadores", "Aleta", JOptionPane.WARNING_MESSAGE );
        }
    }
        

    @FXML
    void escolherIconP0(MouseEvent event) throws IOException {
        index = 0;
        abrirOpcoes(event);
    }

    @FXML
    void escolherIconP1(MouseEvent event) throws IOException{
        index = 1;
        abrirOpcoes(event);
    }

    @FXML
    void escolherIconP2(MouseEvent event) throws IOException{
        index = 2;
        abrirOpcoes(event);
    }

    @FXML
    void escolherIconP3(MouseEvent event) throws IOException{
        index = 3;
        abrirOpcoes(event);
    }

    @FXML
    void escolherIconP4(MouseEvent event) throws IOException{
        index = 4;
        abrirOpcoes(event);
    }

    @FXML
    void escolherIconP5(MouseEvent event) throws IOException{
        index = 5;
        abrirOpcoes(event);
    }

    

    void abrirOpcoes(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("opcoesLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(tela);
        stage.show();
    }
 
}