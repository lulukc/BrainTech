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

import br.com.fiap.Brain_Tech.model.Empresa;
import br.com.fiap.Brain_Tech.service.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@GetMapping
	public ResponseEntity<List<Empresa>> buscarTodas() {
		List<Empresa> list = empresaService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Empresa> buscarUM(@PathVariable Long id) {
		Empresa empresa = empresaService.buscarUM(id);
		return ResponseEntity.ok().body(empresa);
	}

	@PostMapping
	public ResponseEntity<Empresa> insert(@RequestBody Empresa empresa) {
		empresa = empresaService.criar(empresa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresa.getId())
				.toUri();
		return ResponseEntity.created(uri).body(empresa);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestBody Empresa empresa) {
		if (empresa.getPasswordHash() != null) {
			empresa = empresaService.alterarSenha(empresa, id);
		} else {
			empresa = empresaService.alterarDados(empresa, id);
		}
		return ResponseEntity.ok().body(empresa);
	}
}
