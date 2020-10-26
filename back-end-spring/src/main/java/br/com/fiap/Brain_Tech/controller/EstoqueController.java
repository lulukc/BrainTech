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

import br.com.fiap.Brain_Tech.model.Estoque;
import br.com.fiap.Brain_Tech.model.DTO.AtulizarEstoqueDTO;
import br.com.fiap.Brain_Tech.service.EstoqueService;

@RestController
@RequestMapping(value = "/estoques")
public class EstoqueController {

	@Autowired
	private EstoqueService estoqueService;

	@GetMapping
	public ResponseEntity<List<Estoque>> buscarTodas() {
		List<Estoque> list = estoqueService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Estoque> buscarUM(@PathVariable Long id) {
		Estoque estoque = estoqueService.buscarUM(id);
		return ResponseEntity.ok().body(estoque);
	}

	@PostMapping
	public ResponseEntity<Estoque> insert(@RequestBody Estoque estoque) {
		estoque = estoqueService.criar(estoque);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estoque.getId())
				.toUri();
		return ResponseEntity.created(uri).body(estoque);
	}

	@PostMapping(value = "/atualizar")
	public ResponseEntity<Estoque> update(@RequestBody AtulizarEstoqueDTO atulizarEstoqueDTO) {
		Estoque estoque = estoqueService.adicionarEstoque(atulizarEstoqueDTO);
		return ResponseEntity.ok().body(estoque);
	}
}
