package kranidictionary;

import kranidictionary.fonetico.BuscadorFonetico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Esta clase permite hacer consultas fonéticas y de autocompletado a través de una interfaz gráfica.
 * @author JuanJose
 */
public class KraniDictionary extends Application {
    private ObservableList words;
    private final BuscadorFonetico fonetico = new BuscadorFonetico();
    private JFXTextField textField;
    private JFXListView<Label> wordList;
    
    @Override
    public void start(Stage primaryStage) {
        //Load dictionary
        try{
            fonetico.generarDiccionario();
        }catch(FileNotFoundException e){
            System.out.println("Archivo de palabras no encontrado");
        }
        
        //Set general UI
        Color mainColor = Color.CRIMSON;
        
        //Upper pane elements
        textField = new JFXTextField();
        textField.setPromptText("Ingresa una palabra");
        textField.setFocusColor(mainColor);
        JFXComboBox<Label> optionsList = new JFXComboBox<>();
        Label predOption = new Label("Búsqueda Fonética");
        optionsList.getItems().add(predOption);
        optionsList.getItems().add(new Label("Autocompletado"));
        optionsList.setPrefWidth(150);
        optionsList.setValue(predOption);
        JFXButton goButton = new JFXButton("GO");
        goButton.setStyle(".button-raised{\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-button-type: RAISED;\n" +
                "    -fx-text-fill: WHITE;\n" +
                "}");
        goButton.setPrefWidth(40);
        goButton.setBackground(new Background(new BackgroundFill(mainColor, new CornerRadii(3), Insets.EMPTY)));
        HBox upperPane = new HBox();
        upperPane.setPadding(new Insets(5, 10, 5, 10));
        upperPane.setSpacing(10);
        upperPane.getChildren().addAll(textField, optionsList, goButton);
        
        //Center pane
        words = FXCollections.observableArrayList();
        wordList = new JFXListView<>();
        StackPane mainPanel = new StackPane();
        mainPanel.setMaxSize(380, 350);
        mainPanel.getChildren().addAll(wordList);
        
        //Root config
        BorderPane root = new BorderPane();
        root.setTop(upperPane);
        root.setCenter(mainPanel);
        
        //Set scene
        Scene scene = new Scene(root, 400, 400);
        
        //Event handling
        goButton.setOnAction((ActionEvent e) -> {
            showResults();
        });
        goButton.setOnMouseEntered((MouseEvent me) ->{
            scene.setCursor(Cursor.HAND);
        });
        goButton.setOnMouseExited((MouseEvent me) ->{
            scene.setCursor(Cursor.DEFAULT);
        });
        textField.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)){
                showResults();
            }
        });
        
        //Set stage
        primaryStage.setTitle("Práctica Datos");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void showResults(){
        ArrayList<String> list = fonetico.consulta(textField.getText());
        try{
            words = FXCollections.observableArrayList(list);
        }
        catch (NullPointerException e){}
        wordList.setItems(words);
    }
    
    /**
     * Inicializa el programa con interfaz gráfica
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}