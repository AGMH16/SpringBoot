/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DTO.RequestDTO;
import entities.role;
import entities.user;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.roleRepository;
import repository.userRepository;

@Service
public class roleService {

    private final Log LOG = LogFactory.getLog(roleService.class);

    @Autowired
    private roleRepository roleRepository;

    public role createOneRole(role name){
        return roleRepository.save(name);
    }

    public List<role> getAllRole(){
        return roleRepository.findAll();
    }

    public Optional<role> getRoleByName(RequestDTO requestDTO) {
        return roleRepository.findByrolename(requestDTO.getRoleDTO().getRole_name());
    }
}

