/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDTO {

    @JsonProperty("role")
    private roleDTO roleDTO;

    @JsonProperty("user")
    private userDTO userDTO;

    public userDTO getUsuarioDTO() {
        return userDTO;
    }

    public void setUsuarioDTO(userDTO usuarioDTO) {
        this.userDTO = usuarioDTO;
    }

    public roleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(roleDTO rolDTO) {
        this.roleDTO = rolDTO;
    }

}
