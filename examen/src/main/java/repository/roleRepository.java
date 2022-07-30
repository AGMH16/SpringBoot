/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import entities.role;
import net.bytebuddy.TypeCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface roleRepository extends JpaRepository<role,String> {

    @Query("Select r from role u WHERE r.role_name = ?1 ")
    Optional<role> findByrolename(String rol);

    @Query("Select r from role r where r.role_name like ?1%")
    List<role> findAndSort(String role, TypeCache.Sort Sort);

}


