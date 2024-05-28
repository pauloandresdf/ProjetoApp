package com.example.Library.Entidade;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    public static final String Repository = null;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    public List<Emprestimo> getLibraryList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLibraryList'");
    }
    public boolean DisponivelEmprestimo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DisponivelEmprestimo'");
    }
}
