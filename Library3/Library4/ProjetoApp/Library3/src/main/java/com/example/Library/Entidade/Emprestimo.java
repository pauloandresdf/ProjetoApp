package com.example.Library.Entidade;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_de_entrega", nullable = true)
    private LocalDate dataDeEntrega;

    @Column(name = "entrega_realizada", nullable = false)
    private boolean entregaRealizada;

    @OneToOne
    @JoinColumn(name = "livro_id", nullable = true, referencedColumnName = "id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true, referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = true, referencedColumnName = "id")
    private Emprestimo emprestimo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(LocalDate dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public boolean isEntregaRealizada() {
        return entregaRealizada;
    }

    public void setEntregaRealizada(boolean entregaRealizada) {
        this.entregaRealizada = entregaRealizada;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // construtor sem parâmetro
    public Emprestimo() {
    }
}
