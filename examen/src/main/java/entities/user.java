/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class user {

    @Id
    @Column(name = "name")
    private String nombre;

    @Column(name = "username")
    private String nombreUsuario;

    @Column(name = "password")
    private String contrasena;

    @Column(name = "status")
    private Boolean estado;

    @Column(name = "ts_insert")
    private int ts_insert;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<role> roleList;

    public user() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getTs_insert() {
        return ts_insert;
    }

    public void setTs_insert(int ts_insert) {
        this.ts_insert = ts_insert;
    }

    public List<role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<role> roleList) {
        this.roleList = roleList;
    }

}
