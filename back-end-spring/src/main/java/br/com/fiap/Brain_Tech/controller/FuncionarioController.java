package br.com.fiap.Brain_Tech.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.Brain_Tech.model.Funcionario;
import br.com.fiap.Brain_Tech.service.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public ResponseEntity<List<Funcionario>> buscarTodas() {
		List<Funcionario> list = funcionarioService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> buscarUM(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.buscarUM(id);
		return ResponseEntity.ok().body(funcionario);
	}

	@PostMapping
	public ResponseEntity<Funcionario> insert(@RequestBody Funcionario funcionario) {
		funcionario = funcionarioService.criar(funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(funcionario);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
		if (funcionario.getPasswordHash() != null) {
			funcionario = funcionarioService.alterarSenha(funcionario, id);
		} else {
			funcionario = funcionarioService.alterarDados(funcionario, id);
		}
		return ResponseEntity.ok().body(funcionario);
	}
}
