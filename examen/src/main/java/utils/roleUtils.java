/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import DTO.roleDTO;
import entities.role;
import org.springframework.stereotype.Service;

@Service

public class roleUtils {
    public role FillRole(roleDTO roleDTO){
        role role= new role();
            role.setRoleNombre(roleDTO.getRole_name());
        return role;
    }
}
