/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoBaseDatos;

/**
 *
 * @author Jaasiel
 */
public class Datos_empleados {
    private Integer numero_empleadoD;
    private String nombre_empleadoD;
    private String servicioD;
    private String fecha_ingresoD;
    private Integer numero_cuentaD;
    private String vacacionesD;
    private String renunciaD;
    private String finiquitoD;
    private String activoD;

    public Datos_empleados(Integer numero_empleadoD, String nombre_empleadoD, String servicioD, String fecha_ingresoD, Integer numero_cuentaD, String vacacionesD, String renunciaD, String finiquitoD, String activoD) {
        this.numero_empleadoD = numero_empleadoD;
        this.nombre_empleadoD = nombre_empleadoD;
        this.servicioD = servicioD;
        this.fecha_ingresoD = fecha_ingresoD;
        this.numero_cuentaD = numero_cuentaD;
        this.vacacionesD = vacacionesD;
        this.renunciaD = renunciaD;
        this.finiquitoD = finiquitoD;
        this.activoD = activoD;
    }

    public Integer getNumero_empleadoD() {
        return numero_empleadoD;
    }

    public String getNombre_empleadoD() {
        return nombre_empleadoD;
    }

    public String getServicioD() {
        return servicioD;
    }

    public String getFecha_ingresoD() {
        return fecha_ingresoD;
    }

    public Integer getNumero_cuentaD() {
        return numero_cuentaD;
    }

    public String getVacacionesD() {
        return vacacionesD;
    }

    public String getRenunciaD() {
        return renunciaD;
    }

    public String getFiniquitoD() {
        return finiquitoD;
    }

    public String getActivoD() {
        return activoD;
    }
    
}
