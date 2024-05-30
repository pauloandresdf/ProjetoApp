package com.example.Library.Entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table
public class Livro {

    // Contém informações do livro:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = true, length = 100)
    private String titulo;

    @Column(name = "ano_publicacao", nullable = true, length = 100)
    private Integer anoPublicacao;

    @Column(name = "disponivel_emprestimo")
    private boolean dispinivelEmprestimo;

    // getters e setters

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

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }
    //campo setAnoPublicacao
    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    //campo dispinivelEmprestimo
    public void setDisponivelEmprestimo(boolean dispinivelEmprestimo) {
        this.dispinivelEmprestimo = dispinivelEmprestimo;
    }

    //construtor sem parâmetros
    public Livro() {
    }
}