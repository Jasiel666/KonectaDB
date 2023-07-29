package ProyectoBaseDatos;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class PrincipalBDK extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("Ventana_1.fxml"));
       Scene scene = new Scene(root);
       stage.setMaxHeight(768);
       stage.setMaxWidth(1366);
       stage.setTitle("");
       stage.setScene(scene);
       stage.show();
      }
    
}

