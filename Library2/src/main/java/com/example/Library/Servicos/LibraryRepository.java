package com.example.Library.Servicos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.Entidade.Emprestimo;

// Estende JpaRepository para operação de banco de dados:
@Repository
public interface LibraryRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByGeneroAndDisponivelEmprestimo(String genero, boolean dispinivelEmprestimo);
}