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
import com.example.Library.Entidade.Usuario;
import com.example.Library.Servicos.LibraryService;

@RestController
@RequestMapping("/library")

public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/livros")
    public ResponseEntity<List<Emprestimo>> getLivrosDisponiveisGenero(@RequestParam String genero) {
        List<Emprestimo> biblioteca = libraryService.listarLivrosDisponivelGenero(genero);
        if (biblioteca.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(biblioteca);
        }
    }

    @PostMapping("/cadastro-usuario")
    public void cadastrarUsuario(@RequestBody Usuario user) {
        libraryService.cadastrarUsuario(user);
    }

    @PostMapping("/cadastrar-livro/{userId}")
    public void cadastrarLivro(@RequestBody Emprestimo library, @PathVariable Long userID) {
        libraryService.cadastrarLivro(library, userID);
    }

    @GetMapping("/usuario/{userID}")
    public ResponseEntity<Usuario> getUserInfo(@PathVariable Long userID) {
        Usuario user = libraryService.getUserInfo(userID);
        return ResponseEntity.ok().body(user);

    }

    @PostMapping("/emprestimo/{userID}/{livroID}")
    public ResponseEntity<String> realizarEmprestimo(@PathVariable Long userID, @PathVariable Long livroID) {
        String message = libraryService.realizarEmprestimo(userID, livroID);

        if (message.contains("Atenção")) {
            return ResponseEntity.badRequest().body(message);
        } else {
            return ResponseEntity.ok().body(message);
        }

    }

    @PostMapping("/devolucao{userID}/livroID")
    public ResponseEntity<String> realizarDevolucao(@PathVariable Long userID, @PathVariable Long livroID) {
        String message = libraryService.realizarDevolucao(userID, livroID);
        return ResponseEntity.ok().body(message);
    }

}
