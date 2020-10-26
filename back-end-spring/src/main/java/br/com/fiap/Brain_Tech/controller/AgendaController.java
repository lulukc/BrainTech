package br.com.fiap.Brain_Tech.controller;

import java.net.URI;
import java.text.ParseException;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.Brain_Tech.model.Agenda;
import br.com.fiap.Brain_Tech.model.DTO.AgendaCriarDTO;
import br.com.fiap.Brain_Tech.service.AgendaService;

@RestController
@RequestMapping(value = "/agendas")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;

	@GetMapping
	public ResponseEntity<List<Agenda>> buscarTodas() {
		List<Agenda> list = agendaService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Agenda> buscarUM(@PathVariable Long id) {
		Agenda agenda = agendaService.buscarUM(id);
		return ResponseEntity.ok().body(agenda);
	}

	@GetMapping(value = "/buscar")
	public ResponseEntity<List<Agenda>> buscarEntreData(@RequestParam("data") String dataParametro)
			throws ParseException {
		List<Agenda> agendas = agendaService.buscarEntreData(dataParametro);
		return ResponseEntity.ok().body(agendas);
	}

	@PostMapping
	public ResponseEntity<Agenda> insert(@RequestBody AgendaCriarDTO agendaDTO) throws ParseException {
		Agenda agenda = agendaService.criar(agendaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agenda.getId()).toUri();
		return ResponseEntity.created(uri).body(agenda);
	}

}
