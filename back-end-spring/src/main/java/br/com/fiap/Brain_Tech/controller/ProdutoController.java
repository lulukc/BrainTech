package br.com.fiap.Brain_Tech.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.Brain_Tech.model.Produto;
import br.com.fiap.Brain_Tech.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> buscarTodas() {
		List<Produto> list = produtoService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscarUM(@PathVariable Long id) {
		Produto produto = produtoService.buscarUM(id);
		return ResponseEntity.ok().body(produto);
	}

	@PostMapping
	public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
		produto = produtoService.criar(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(produto);
	}

//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
//		if (produto.getPasswordHash() != null) {
//			produto = produtoService.alterarSenha(produto, id);
//		} else {
//			produto = produtoService.alterarDados(produto, id);
//		}
//		return ResponseEntity.ok().body(produto);
//	}
}
