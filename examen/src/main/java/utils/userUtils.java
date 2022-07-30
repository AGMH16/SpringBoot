/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import DTO.userDTO;
import entities.user;
import org.springframework.stereotype.Service;


@Service
public class userUtils {
    
    public user FillUser(userDTO userDTO){
        user user= new user();
            user.setNombre(userDTO.getName());
            user.setNombreUsuario(userDTO.getUsername());
            user.setContrasena(userDTO.getPassword());
            user.setEstado(userDTO.getStatus());
            user.setTs_insert(user.getTs_insert());
        return user;
    }
}