/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProyectoBaseDatos;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jaasiel
 */
public class Ventana_principalController implements Initializable {

    @FXML
    private TextField no_empleado;
    @FXML
    private TextField nombre_empleado;
    @FXML
    private TextField servicio;
    @FXML
     private DatePicker ingreso;
    @FXML
    private TextField no_cuenta;
    @FXML
    private TextField vacaciones;
    @FXML
     private DatePicker renuncia;
    @FXML
    private TextField finiquito;
     @FXML
    private MFXToggleButton activo;
     
     // botones
     @FXML
     private Button insertar;
     @FXML
     private Button actualizar;
     @FXML
     private Button eliminar;
     @FXML
     private Button limpiar;
     @FXML
     private Button salir;
     @FXML
     private TableView <Datos_empleados> datos;
     @FXML
     private TableColumn<Datos_empleados, Integer> t_no_empleado;
     @FXML
     private TableColumn<Datos_empleados, String> t_nombre_empleado;
     @FXML
     private TableColumn<Datos_empleados, String> t_servicio;
     @FXML
     private TableColumn<Datos_empleados, String> t_ingreso;
     @FXML
     private TableColumn<Datos_empleados, Integer> t_no_cuenta;
     @FXML
     private TableColumn<Datos_empleados, String> t_vacaciones;
     @FXML
     private TableColumn<Datos_empleados, String> t_renuncia;
     @FXML
     private TableColumn<Datos_empleados, String> t_finiquito;
     @FXML
     private TableColumn<Datos_empleados, String> t_activo;
     
     @FXML
     private void handleButtonAction(ActionEvent evento){
         
         if(evento.getSource()==insertar){
             botonInsertar();
         }else if(evento.getSource()==actualizar){
         botonActualizar();
        }else if(evento.getSource()==eliminar){
            botonEliminar();
        }else if(evento.getSource()==limpiar){
            botonLimpiar();
     }
     }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDatos();
    }    
   
     private Connection conexion;
     private PreparedStatement statement1;
     private ResultSet resultado1;

    public Connection conexionBD(){
       
       try{
           conexion=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/datos?zeroDateTimeBehavior=CONVERT_TO_NULL","root","jaasiel");
           return conexion;
       }catch(SQLException e){
   }
        return null;
   }
    
    
    public ObservableList<Datos_empleados> getDatos_empleadosList(){
        ObservableList<Datos_empleados> datosList = FXCollections.observableArrayList();
        Connection conexion1 = conexionBD();
        String sql="SELECT * FROM datos.empleados";
       
      try{
          statement1=conexion1.prepareStatement(sql);
          resultado1=statement1.executeQuery(sql);
           Datos_empleados datos_empleados;
          while(resultado1.next()){
              datos_empleados= new Datos_empleados(resultado1.getInt("numero_empleado"),resultado1.getString("nombre_empleado"),resultado1.getString("servicio"),resultado1.getString("fecha_ingreso"),resultado1.getInt("numero_cuenta"),resultado1.getString("vacaciones"),resultado1.getString("renuncia"),resultado1.getString("finiquito"),resultado1.getString("activo"));
              datosList.add(datos_empleados);
              
          }
          
          
      }catch(SQLException e){
         
      }  
         return datosList;
    }
    
    /**
     *
     */
    public void showDatos(){
         ObservableList<Datos_empleados> list= getDatos_empleadosList();
         t_no_empleado.setCellValueFactory(new PropertyValueFactory<>("numero_empleadoD"));
         t_nombre_empleado.setCellValueFactory(new PropertyValueFactory<>("nombre_empleadoD"));
         t_servicio.setCellValueFactory(new PropertyValueFactory<>("servicioD"));
         t_ingreso.setCellValueFactory(new PropertyValueFactory<>("fecha_ingresoD"));
         t_no_cuenta.setCellValueFactory(new PropertyValueFactory<>("numero_cuentaD"));
         t_vacaciones.setCellValueFactory(new PropertyValueFactory<>("vacacionesD"));
         t_renuncia.setCellValueFactory(new PropertyValueFactory<>("renunciaD"));
         t_finiquito.setCellValueFactory(new PropertyValueFactory<>("finiquitoD"));
         t_activo.setCellValueFactory(new PropertyValueFactory<>("activoD"));
         datos.setItems(list);
          
    }
    
    
    
   private void botonInsertar(){
        if(activo.isSelected()){
            String sql1 = "INSERT INTO datos.empleados VALUES("+no_empleado.getText()+",'"+nombre_empleado.getText()+"','"+servicio.getText()+"','"+ingreso.getValue()+"',"+no_cuenta.getText()+",'"+vacaciones.getText()+"','"+renuncia.getValue()+"','"+finiquito.getText()+"','si')";
                excecuteQuery(sql1);
                showDatos();
        }else{
             String sql1 = "INSERT INTO datos.empleados VALUES("+no_empleado.getText()+",'"+nombre_empleado.getText()+"','"+servicio.getText()+"','"+ingreso.getValue()+"',"+no_cuenta.getText()+",'"+vacaciones.getText()+"','"+renuncia.getValue()+"','"+finiquito.getText()+"','no')";
                excecuteQuery(sql1);
                showDatos();
        }
        
                }
   
   private void botonActualizar(){
       if(activo.isSelected()){
       String sql1= "UPDATE datos.empleados SET  nombre_empleado='"+nombre_empleado.getText()+"',servicio='"+servicio.getText()+"',fecha_ingreso='"+ingreso.getValue()+"',numero_cuenta="+no_cuenta.getText()+",vacaciones='"+vacaciones.getText()+"',renuncia='"+renuncia.getValue()+"',finiquito='"+finiquito.getText()+"',activo='si'"+"WHERE numero_empleado = "+no_empleado.getText()+"";
                excecuteQuery(sql1);
                showDatos();
       }else{
           String sql1= "UPDATE datos.empleados SET  nombre_empleado='"+nombre_empleado.getText()+"',servicio='"+servicio.getText()+"',fecha_ingreso='"+ingreso.getValue()+"',numero_cuenta="+no_cuenta.getText()+",vacaciones='"+vacaciones.getText()+"',renuncia='"+renuncia.getValue()+"',finiquito='"+finiquito.getText()+"',activo='no'"+"WHERE numero_empleado = "+no_empleado.getText()+"";
                excecuteQuery(sql1);
                showDatos();
       }
       
   }
   
   private void botonEliminar(){
       String sql1= "DELETE FROM datos.empleados WHERE numero_empleado= " + no_empleado.getText()+"";
       excecuteQuery(sql1);
       showDatos();
       
   }
   
   public void botonSalir(ActionEvent event) throws IOException{
       salir.getScene().getWindow();
       Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ventana_1.fxml"));
       Scene tableViewScene=new Scene(tableViewParent);
       
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(tableViewScene);
       window.show();
       
   }
   
   private void botonLimpiar(){
       no_empleado.setText(null);
       nombre_empleado.setText(null);
       servicio.setText(null);
       ingreso.setValue(null);
       no_cuenta.setText(null);
       vacaciones.setText(null);
       renuncia.setValue(null);
       finiquito.setText(null);
       
       
   }
   

    private void excecuteQuery(String sql1) {
       Connection conexion1 = conexionBD();
       Statement statement;
       try{
           statement=conexion1.createStatement();
           statement.executeUpdate(sql1);
           
       }catch(SQLException e){
           
       }
    }
    
    @FXML
    private void handleMouseAction(javafx.scene.input.MouseEvent event) {
        Datos_empleados dato=datos.getSelectionModel().getSelectedItem();
       try{
           no_empleado.setText(Integer.toString(dato.getNumero_empleadoD()));
       nombre_empleado.setText(dato.getNombre_empleadoD());
       servicio.setText(dato.getServicioD());
       ingreso.setValue(LocalDate.parse(dato.getFecha_ingresoD()));
       no_cuenta.setText(Integer.toString(dato.getNumero_cuentaD()));
       vacaciones.setText(dato.getVacacionesD());
       renuncia.setValue(LocalDate.parse(dato.getRenunciaD()));
       finiquito.setText(dato.getFiniquitoD());
     }catch(Exception e){
         
         
     }
       
      
    }

    
    
    
}
