package com.example.Library.Controle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.Entidade.Emprestimo;
import com.example.Library.Entidade.Livro;
import com.example.Library.Entidade.Usuario;
import com.example.Library.Servicos.LibraryService;

@RestController
@RequestMapping("/library")

public class LibraryController {

    @Autowired
    private LibraryService libraryService;

     @GetMapping("/livros")
    public ResponseEntity<List<Livro>> getLivrosDisponiveisGenero(@RequestParam String genero) {
        List<Livro> biblioteca = libraryService.listarLivrosDisponivelGenero(genero);
        if (biblioteca.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(biblioteca);
        }
    } 

    @PostMapping("/usuario")
    public void cadastrarUsuario(@RequestBody Usuario usuario) {
        libraryService.cadastrarUsuario(usuario);

    }

    @PostMapping("/livro")
    public void cadastrarLivro(Livro livro, Long usuarioId) {
        Usuario usuario = userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Verifica se o usuário tem empréstimos pendentes
        if (usuario.getEmprestimos().stream().anyMatch(e ->!e.isEntregaRealizada())) {
            throw new IllegalArgumentException("Usuário possui empréstimos pendentes");
        }

        // Verifica se o número máximo de livros cadastrados foi atingido
        if (usuario.getEmprestimos().size() >= 10) {
            throw new IllegalArgumentException("Número máximo de livros cadastrados atingido");
        }

        livro.setUsuario(usuario);
        try {
            bookRepository.save(livro);
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao cadastrar livro: " + exception.getMessage());
        }
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = libraryService.getUsuarios();
        return ResponseEntity.ok().body(usuarios);

    }

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> getLivros() {
        List<Livro> livros = libraryService.getLivros();
        return ResponseEntity.ok().body(livros);

    }

    @PostMapping("/emprestimo")
    public ResponseEntity<String> realizarEmprestimo(@RequestBody Emprestimo emprestimo) {
        String mensagem = libraryService.realizarEmprestimo(emprestimo);

        // contains verificar se um determinado elemento está presente em uma coleção
        if (mensagem.contains("Atenção")) {
            return ResponseEntity.badRequest().body(mensagem);
        } else {
            return ResponseEntity.ok().body(mensagem);
        }

    }

    @PostMapping("/devolucao")
    public ResponseEntity<String> realizarDevolucao(@PathVariable Long usuarioId, @PathVariable Long livroId) {
        String mensagem = libraryService.realizarDevolucao(usuarioId, livroId);
        return ResponseEntity.ok().body(mensagem);
    }

}
