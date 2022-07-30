/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import entities.user;
import net.bytebuddy.TypeCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user,String> {

    @Query("Select u from user u WHERE u.nameUser = ?1 ")
    Optional<user> findByusername(String nombreUsuario);

    @Query("Select u from Usuario u where u.nombreUsuario like ?1%")
    List<user> findAndSort(String nombreUsuario, TypeCache.Sort Sort);

    List<user> findBynombreUsuario(String nombreUsuario);

    @Query("Select u from user u WHERE u.id = ?1 ")
    Optional<user> findById(int id);
}
