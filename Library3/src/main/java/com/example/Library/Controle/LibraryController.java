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
    public void cadastrarLivro(@RequestBody Livro livro/*, @PathVariable Long usuarioId*/) {
        libraryService.cadastrarLivro(livro/*, usuarioId*/);
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
