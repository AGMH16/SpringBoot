/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author usuario
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class role {
    
    @Id
    @Column(name = "role_name")
    private String roleNombre;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "identificador", referencedColumnName = "role_name")
    @JsonIgnoreProperties("roleList")
    private user usuario;
    
    public role(){
    }

    public String getRoleNombre() {
        return roleNombre;
    }

    public void setRoleNombre(String roleNombre) {
        this.roleNombre = roleNombre;
    }

    public user getUsuario() {
        return usuario;
    }

    public void setUsuario(user usuario) {
        this.usuario = usuario;
    }
    
}
