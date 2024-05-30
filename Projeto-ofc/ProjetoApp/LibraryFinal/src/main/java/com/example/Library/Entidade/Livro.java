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

    @Column(name = "genero", nullable = true, length = 100)
    private String genero;

    @Column(name = "ano_publicacao", nullable = true, length = 100)
    private Integer anoPublicacao;



    @Column(name = "disponivel_emprestimo")
    private boolean disponivelEmprestimo;

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
    //campo disponivelEmprestimo
    public void setDisponivelEmprestimo(boolean disponivelEmprestimo) {
        this.disponivelEmprestimo = disponivelEmprestimo;
    }

    public boolean isDisponivelEmprestimo(){
        return disponivelEmprestimo;
    }

    //construtor sem parâmetros
    public Livro() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isdisponivelEmprestimo() {
        return disponivelEmprestimo;
    }

    public void setdisponivelEmprestimo(boolean disponivelEmprestimo) {
        this.disponivelEmprestimo = disponivelEmprestimo;
    }
}