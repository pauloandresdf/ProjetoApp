package com.example.Library.Entidade;

import org.hibernate.mapping.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;

@Entity
public class Emprestimo {

    // Contém informações do livro:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "genero", nullable = false, length = 100)
    private String genero;

    @Column(name = "disponivel-emprestimo", nullable = false)
    private boolean disponivelEmprestimo;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean getDisponivelEmprestimo() {
        return disponivelEmprestimo;
    }

    public void setDisponivelEmprestimo(boolean disponivelEmprestimo) {
        this.disponivelEmprestimo = disponivelEmprestimo;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;

    }
}