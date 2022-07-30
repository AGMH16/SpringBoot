/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DTO.RequestDTO;
import entities.user;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import service.userService;

@CrossOrigin(origins = "https://base-de-datos-frontend-14gczqhqb-tatobig.vercel.app/")
@RestController
@RequestMapping("/users")
public class userController {

    private final userService usuarioService;

    public userController(userService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    List<user> getUsers() {
        return usuarioService.getAllUsers();
    }

    @PostMapping("/")
    ResponseEntity<user> newUser(@RequestBody user user) {
        System.out.println(user.toString());
        return new ResponseEntity<>(usuarioService.createOneUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Integer id) /*throws BusinessException*/ {
        usuarioService.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
