package ProyectoBaseDatos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Jaasiel
 */
public class Ventana_1Controller implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
    //interfaz login
    @FXML
    private TextField usuario_konecta;
    @FXML
    private PasswordField contrasena_konecta;
    @FXML
    private Button ingresar_konecta;
   
   //interfaz registro
    @FXML
    private Button regresar_boton;
    @FXML
    private TextField nombre_completo;
    @FXML
    private TextField n_nombre_usuario;
    @FXML
    private PasswordField n_contrasena;
    @FXML 
    private PasswordField n_verificacion;
    @FXML
    private Button crear;
    @FXML
    private BorderPane ventana2;
    @FXML
   public void change_scene(ActionEvent event) throws IOException{
       
       Parent tableViewParent = FXMLLoader.load(getClass().getResource("ventana_2.fxml"));
       Scene tableViewScene=new Scene(tableViewParent);
       
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(tableViewScene);
       window.show();
   }
   
    @FXML
    public void change_scene1(ActionEvent event) throws IOException{
        regresar_boton.getScene().getWindow();
       Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ventana_1.fxml"));
       Scene tableViewScene=new Scene(tableViewParent);
       
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(tableViewScene);
       window.show();
   }
  
   
   private Connection conexion;
   private PreparedStatement statement;
   private ResultSet resultado;

   public Connection conexion_a_BD(){
       
       try{
           
           conexion=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/konectadb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","jaasiel");
           return conexion;
       }catch(SQLException e){
   }
        return null;
   }
   

   public void inicio_sesion(ActionEvent event) throws IOException, ClassNotFoundException{
     conexion= conexion_a_BD();
       try{
           String sql= "SELECT * FROM usuario WHERE nombre_usuario=? and contrasena_usuario=?";
           statement=conexion.prepareStatement(sql);
           statement.setString(1, usuario_konecta.getText());
           statement.setString(2, contrasena_konecta.getText());
           resultado=statement.executeQuery();
            
           if(resultado.next()){
               Alert alerta=new Alert(AlertType.INFORMATION);
               alerta.setHeaderText("");
               alerta.setContentText("Accediste exitosamente");
               alerta.showAndWait();
               ingresar_konecta.getScene().getWindow().hide();
               Parent root=FXMLLoader.load(getClass().getResource("ventana_principal.fxml"));
               Scene scene= new Scene(root);
               Stage stage=new Stage();
               stage.setScene(scene);
               stage.setTitle("KonectaDB");
               stage.show();
           }else{
               Alert alerta=new Alert(AlertType.ERROR);
               alerta.setHeaderText("Error");
               alerta.setContentText("Usuario o Contraseña invalidos");
               alerta.show();
               
              
           }
           
           
       }catch(SQLException e){
           
       }
   }
   
  
   public void registro(ActionEvent event){
      
       conexion=conexion_a_BD();
       
       try{
           String sql="INSERT INTO usuario(nombre_usuario,contrasena_usuario,nombre_completo) VALUES(?,?,?)";
           statement=conexion.prepareStatement(sql);
           statement.setString(1, n_nombre_usuario.getText());
           statement.setString(2, n_contrasena.getText());
           statement.setString(3, nombre_completo.getText());
           String confirmacion=n_contrasena.getText();
           String confirmacion1= n_verificacion.getText();
           
           if(confirmacion.equals(confirmacion1)){
                statement.execute();
                Alert alerta=new Alert(AlertType.CONFIRMATION);
               alerta.setHeaderText("");
               alerta.setContentText("Cuenta creada con exito");
               alerta.show();
           }else{
               Alert alerta=new Alert(AlertType.ERROR);
               alerta.setHeaderText("Error");
               alerta.setContentText("Las contraseñas no coinciden");
               alerta.show();
           }
        }catch(SQLException e){
            
        }
        
    }
    
   
       @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }
}

