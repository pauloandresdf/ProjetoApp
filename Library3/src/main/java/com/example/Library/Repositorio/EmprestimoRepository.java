package com.example.Library.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.Entidade.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuarioIdAndEntregaRealizadaFalse(Long usuarioId);
    
}