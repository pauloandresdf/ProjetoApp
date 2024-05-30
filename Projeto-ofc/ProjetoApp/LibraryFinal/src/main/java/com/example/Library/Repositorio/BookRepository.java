package com.example.Library.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.Entidade.Livro;

@Repository
public interface BookRepository extends JpaRepository<Livro, Long> {

}
