package com.example.Library.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.Entidade.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

}