/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DTO.RequestDTO;
import entities.user;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import repository.userRepository;

@Service
public class userService {

    private final Log LOG = LogFactory.getLog(userService.class);

    @Autowired
    private userRepository usuarioRepository;

    public user createOneUser(user usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<user> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Optional<user> getUserById(RequestDTO requestDTO) {
        return usuarioRepository.findByusername(requestDTO.getUsuarioDTO().getUsername());
    }
    
    public void remove(Integer id) /*throws BusinessException*/ {
        Optional<user> deleted = usuarioRepository.findById(id);
        /*if (deleted.isEmpty()) {
            throw new userException("BAD_REQUEST", "El cliente no existe");
        }*/
        usuarioRepository.delete(deleted.get());
    }
}
