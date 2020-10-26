package br.com.fiap.Brain_Tech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Brain_Tech.model.Pedido;
import br.com.fiap.Brain_Tech.model.DTO.PedidoDTO;
import br.com.fiap.Brain_Tech.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> buscarTodas() {
		List<Pedido> list = pedidoService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> buscarUM(@PathVariable Long id) {
		Pedido pedido = pedidoService.buscarUM(id);
		return ResponseEntity.ok().body(pedido);
	}

	@GetMapping(value = "/empresa/{id}")
	public ResponseEntity<List<Pedido>> buscarPorEmpresa(@PathVariable Long id) {
		List<Pedido> list = pedidoService.buscarPorEmpresa(id);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Pedido> insert(@RequestBody PedidoDTO pedidoDTO) {
		Pedido pedido = pedidoService.criar(pedidoDTO);
		return ResponseEntity.ok().body(pedido);
	}

	@GetMapping(value = "/retirada/{id}")
	public ResponseEntity<Pedido> retirada(@PathVariable Long id) {
		Pedido pedido = pedidoService.atualizar(id);
		return ResponseEntity.ok().body(pedido);
	}

}
