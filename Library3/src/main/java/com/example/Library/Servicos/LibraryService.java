package com.example.Library.Servicos;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Library.Entidade.Emprestimo;
import com.example.Library.Entidade.Livro;
import com.example.Library.Entidade.Usuario;
import com.example.Library.Repositorio.LibraryRepository;
import com.example.Library.Repositorio.BookRepository;
import com.example.Library.Repositorio.EmprestimoRepository;
import com.example.Library.Repositorio.UserRepository;

@Service
public class LibraryService {
    
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<Livro> listarLivrosDisponivelGenero(String genero) {
        return libraryRepository.findByGeneroAndDisponivelEmprestimo(genero, true);
    }

    public void cadastrarUsuario(Usuario usuario) {
        try {
            userRepository.save(usuario);
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao cadastrar usuário: " + exception.getMessage());
        }
    }

    public void cadastrarLivro(Livro livro, Long usuarioId) {
        Usuario usuario = userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        if (usuario.getEmprestimos().size() >= 10) {
            throw new IllegalArgumentException("Número máximo de livros cadastrados atingido");
        } 

        try {
            bookRepository.save(livro);
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao cadastrar livro: " + exception.getMessage());
        }
    }

    public Usuario getInfoUsuario(Long usuarioId) {
        return userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public String realizarEmprestimo(Emprestimo emprestimo) {
        Usuario usuario = userRepository.findById(emprestimo.getUsuario().getId()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Livro livro = bookRepository.findById(emprestimo.getLivro().getId()).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        
         if (!livro.isDisponivelEmprestimo()) {
            return "Atenção: Livro não está disponível para empréstimo";
        
        }
        if (usuario.getEmprestimos().size() >= 3) {
            return "Atenção: Número máximo de livros emprestados atingido";
        }

        for (Emprestimo emprestimoExistente : usuario.getEmprestimos()) {
            // equals em Java é usado para comparar dois objetos e determinar se eles são iguais
            if (emprestimoExistente.getLivro().equals(livro)) {
                return "Atenção: Você já possui esse livro emprestado";
        }
    }
        
        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setLivro(livro);
        novoEmprestimo.setEntregaRealizada(false); 

        livro.isDisponivelEmprestimo(false);
        try {
            bookRepository.save(livro);
            usuario.getEmprestimos().add(novoEmprestimo);
            userRepository.save(usuario);
        } catch (Exception exception) {
            throw new RuntimeErrorException("Erro ao realizar empréstimo: " + exception.getMessage()); {
        }

        return "Livro emprestado com sucesso";
    }

    public String realizarDevolucao(Long usuarioId, Long livroId) {
        Usuario usuario = userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Livro livro = bookRepository.findById(livroId).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        
        Emprestimo emprestimoEncontrado = null;
        for (Emprestimo emprestimo : usuario.getEmprestimos()) {
            if (emprestimo.getLivro().equals(livro) && !emprestimo.isEntregaRealizada()) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if (emprestimoEncontrado == null) {
            return "Você não possui esse livro emprestado ou ele já foi devolvido.";
        }

        livro.setDisponivelEmprestimo(true);
        try {
            bookRepository.save(livro);
            emprestimoEncontrado.setEntregaRealizada(true);
            userRepository.save(usuario);
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao realizar devolução: " + exception.getMessage());
        }

        return "Livro devolvido com sucesso";
    }

    public List<Usuario> getUsuarios() {
        return userRepository.findAll();
    }

    public List<Livro> getLivros() {
        return bookRepository.findAll();
    }

    public boolean usuarioEmprestimoNaoEntregues(Long usuarioId) {
        List<Emprestimo> emprestimosNaoEntregues = emprestimoRepository.findByUsuarioIdAndEntregaRealizadaFalse(usuarioId);
        return !emprestimosNaoEntregues.isEmpty();
    }
}

