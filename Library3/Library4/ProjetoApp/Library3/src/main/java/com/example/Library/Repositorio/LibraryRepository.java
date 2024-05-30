package com.example.Library.Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.Entidade.Livro;

// Estende JpaRepository para operação de banco de dados:
@Repository
public interface LibraryRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByGeneroAndDisponivelEmprestimo(String genero, boolean dispinivelEmprestimo);
}
