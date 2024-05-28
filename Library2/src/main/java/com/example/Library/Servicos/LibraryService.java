package com.example.Library.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Library.Entidade.Emprestimo;
import com.example.Library.Entidade.Usuario;
import com.example.Library.Repositorio.LibraryRepository;

@Service
public class LibraryService {
    
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Emprestimo> listarLivrosDisponivelGenero(String genero) {
        return libraryRepository.findByGeneroAndDisponivelEmprestimo(genero, true);
    }

    public void cadastrarUsuario(Usuario user) {
        userRepository.save(user);
    }

    public void cadastrarLivro(Emprestimo library, Long userId) {
        Usuario user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        if (user.getLibraryList().size() >= 3) {
            throw new IllegalArgumentException("Número máximo de livros cadastrados atingido");
        }

        library.setUser(user);
        libraryRepository.save(library);
    }

    public Usuario getUserInfo(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public String realizarEmprestimo(Long userId, Long livroId) {
        Usuario user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Emprestimo library = libraryRepository.findById(livroId).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        
        if (!library.getDisponivelEmprestimo()) {
            return "Atenção: Livro não está disponível para empréstimo";
        
        }
        if (user.getLibraryList().size() >= 3) {
            return "Atenção: Número máximo de livros emprestados atingido";
        }

        if (user.getLibraryList().contains(library)) {
            return "Atenção: Você já possui esse livro emprestado";
        }

        library.setDisponivelEmprestimo(false);
        Emprestimo emprestimo = libraryRepository.save(library);
        if (emprestimo != null) {
            library.setId(emprestimo.getId());
        }
        user.getLibraryList().add(library);
        userRepository.save(user);

        return "Livro emprestado com sucesso";
    }

    public String realizarDevolucao(Long userId, Long livroId) {
        Usuario user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Emprestimo library = libraryRepository.findById(livroId).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        
        if (library.getDisponivelEmprestimo()) {
            return "Livro já foi devolvido anteriormente";
        }
        if (!user.getLibraryList().contains(library)) {
            return "Você não possui esse livro emprestado";
        }

        library.setDisponivelEmprestimo(true);
        libraryRepository.save(library);
        user.getLibraryList().remove(library);
        userRepository.save(user);

        return "Livro devolvido com sucesso";
    }
}

