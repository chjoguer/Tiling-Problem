/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mosaico.Mosaico;

/**
 *
 * @author Christian Guerrero
 */
public class NewFXMain extends Application {
   
    private HBox hb;
    private HBox hb2;
    private VBox vb;
    private Label coordenadas;
    private Label tamanio;
    private TextField coorx;
    private TextField coory;
    private ComboBox<String> size ;
    private Button btn;
    private Mosaico matriz;
    Pane root ;

    
    
    public void armarSetCoordenadas(){
        root=new Pane();
        coordenadas = new Label("COORDENADAS");
        tamanio = new Label("TAMAÑO 2^n: ");
        coorx = new TextField();
        coory = new TextField();
        btn = new Button(">");
        coorx.setMaxSize(30, 30);     
        coory.setMaxSize(30, 30);
        size = new ComboBox();
        size.getItems().addAll("1","2","3","4","5","6","7","8","9");
        size.setPromptText("Size");
        btn.setPrefSize(40,10);
        hb = new HBox(10,coordenadas,coorx,coory);
        hb2 = new HBox(10,tamanio,size,btn);
        
        
        vb = new VBox(20,new Text("OPCIONES: "),hb2,hb);
        vb.setLayoutY(250);
        vb.setLayoutX(20);
        
    }
    public void validarIngresarDatos(){
        String cx = coorx.getText();
        String cy = coory.getText();
        String opt;
        if(cx.equals("")||cy.equals("")||Integer.parseInt(cx)<0||Integer.parseInt(cy)<0||size.getValue()==null){
            System.out.println("Asegurese de ingresar los datos correctamente...");
            size.setValue("1");
        }else{
            opt = size.getValue(); 
            matriz = new Mosaico((int)Math.pow(2, Integer.parseInt(opt)));    
            matriz.matrizPane();
            matriz.posMosaico(0, 0, Integer.parseUnsignedInt(cx)-1,Integer.parseInt(cy)-1,matriz.getSize());
            matriz.outputMosaico();
            System.out.println("");
            System.out.println("");
            matriz.getRoot().setLayoutY(50);
            matriz.getRoot().setLayoutX(300);
            root.getChildren().addAll(matriz.getRoot());
        }
    }
    @Override
    public void start(Stage primaryStage) {
        armarSetCoordenadas();
        btn.setOnAction((event) -> {
            validarIngresarDatos();
        });
        Text txt = new Text("PROBLEMA DE MOSAICOS");
        txt.setLayoutY(25);
        txt.setLayoutX(320);
        txt.setFont(Font.font(25));
        
       root.getChildren().addAll(txt,vb);
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Problema Mosaicos!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
