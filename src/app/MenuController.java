package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button Instrucoes;

    @FXML
    private Button jogar;

    @FXML
    private Button creditos;

    @FXML
    void Iniciar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selecaoLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(tela);
        stage.show();
    }

    @FXML
    void mostrarInstrucoes(ActionEvent event) {
        String instrucoes = 
        "Bem-vindo à Pega em 6!\n\n" +
        "Instruções:\n" +
        "1. Escolha de jogadores:\n" +
        "   - Escolha o nome e Icon do seu personagem.\n" +
        "   - Aperte em \"+\" para adicionar mais um jogador.\n" +
        "   - São no mínimo 3 jogares e no máximo 6.\n"+
        "2. Regras:\n" +
        "   - O jogo possui 109 cartas numeradas de 1 a 109.\n"+
        "   - Cada carta vale 1 ponto com as seguintes exceções:\n"+
        "       - Cartas terminadas com o dígito 5 valem 1 ponto extra;\n"+
        "       - Cartas múltiplas de 10 valem 2 pontos extras;\n"+
        "       - Cartas com dois dígitos repetidos valem 4 pontos extras;\n"+
        "       - As estrelas em cima da carta são seus pontos.\n"+
        "   - No início da partida cada jogador compra 12 cartas.\n" +
        "   - No início da partida 5 cartas são distribuidas na 1 coluna do tabuleiro.\n" +
        "3. Rodada:\n" +
        "   - Cada jogador escolhe sua carta.\n" +
        "   - A ordem dos jogadores é definida pela ordem crescente das suas cartas.\n" +
        "   - Posiciona-se a carta selecionada à direita de seu Antecessor mais próximo.\n" +
        "       - Se a carta não tiver antecessor, o jogador compra a linha com a maior carta\n"+
        "           e posiciona a carta jogadada na mesma linha.\n" +
        "       - Se a carta tiver que ser jogada na quinta linha, o jogador compra a linha em\n"+
        "           questão e posiciona a carta jogadada na mesma linha.\n" +
        "4. Fim do jogo:\n" +
        "   - Após se passar 12 rodadas o jogo termina.\n" +
        "   - É somado os pontos das cartas compradas.\n" +
        "   - O jogador com menos pontos é o vencedor.\n";
     
    Alert alert = new Alert(Alert.AlertType.NONE);
    alert.setTitle("Instruções");
    alert.setHeaderText(null);

    DialogPane dialogPane = new DialogPane();
    dialogPane.setContentText(instrucoes);
    dialogPane.getButtonTypes().add(ButtonType.OK);
    dialogPane.setMinHeight(Region.USE_PREF_SIZE);
    dialogPane.setMinWidth(Region.USE_PREF_SIZE);
    dialogPane.setPrefSize(600, 600);
    dialogPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    dialogPane.setStyle("-fx-font-size: 13");

    alert.setDialogPane(dialogPane);

    alert.showAndWait();
    }

    @FXML
    void mostrarCredito(ActionEvent event) {
        String credito = 
            "Progamação e Interface gráfica\n"+
            " - João Vitor Silva\n"+
            " - Valéria Faccin\n\n"+
            "Desing das Cartas:\n"+
            " - João Vitor Silva\n\n"+
            "Desing dos ícones dos jogadores:\n"+
            " - Canva";
    
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Crétidos");
        alert.setHeaderText(null);

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContentText(credito);
        dialogPane.getButtonTypes().add(ButtonType.OK);
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMinWidth(Region.USE_PREF_SIZE);
        dialogPane.setPrefSize(600, 600);
        dialogPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        dialogPane.setStyle("-fx-font-size: 16");

        alert.setDialogPane(dialogPane);
        alert.showAndWait();
    }

}
