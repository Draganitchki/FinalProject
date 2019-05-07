/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.stage.Window;
import java.io.FileReader;
import java.util.Arrays;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javax.swing.JTextArea;
import javafx.scene.shape.Rectangle;
import java.awt.Color;

/**
 *
 * @author Me
 */
public class InfoBox {


    InfoBox(){
        
        
    }

    public static void display(String title, String message){
        Stage window = new Stage();
        
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        ScrollPane text = new ScrollPane(label);
        text.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        text.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        text.setPrefSize(300, 300);
        text.setContent(label);
        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e -> window.close());
        
        
        VBox layout = new VBox(10);
        layout.setMinWidth(10);
        layout.getChildren().addAll(text, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    
    }
    //no idea if we'll even use this tho???? 
    public void matchInfo(League l){
        String[][] info = new String[3][4];
      //info[0][0] = l-> get match --> find a method to do this
      //info[0][1] = l-> get team 1
      //info[0][2] = l-> get team 2
      info[1][0] = "win";
      //info[1][1] = l-> team 1 won or not
      //info[1][2] = l-> team 2 won or not
      info[2][0] = "score";
      //info[2][1] = l-> team 1 score
      //info[2][2] = l-> team 2 score
      info[3][0] = "penalties";
      //info[3][1] = l-> team 1 penalities
      //info[3][2] = l-> team 2 penalities 
      String infoStr = Arrays.deepToString(info);
      display("Match Information",infoStr);
    }
    public void instructions(){
        FileInitializer fz = new FileInitializer();
        fz.readInstructions();
        display("Instructions", fz.getInstructions());
    }






    

        
    
}
