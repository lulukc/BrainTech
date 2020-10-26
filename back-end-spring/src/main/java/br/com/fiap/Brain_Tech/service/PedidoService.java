package br.com.fiap.Brain_Tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.Empresa;
import br.com.fiap.Brain_Tech.model.Pedido;
import br.com.fiap.Brain_Tech.model.DTO.ItemPedidoDTO;
import br.com.fiap.Brain_Tech.model.DTO.PedidoDTO;
import br.com.fiap.Brain_Tech.model.enums.StatusPagamento;
import br.com.fiap.Brain_Tech.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoService itemPedidoService;

	public Pedido buscarUM(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		Pedido pedido = obj.get();
		pedido.calcularTotal();
		return pedido;

	}

	public List<Pedido> buscarPorEmpresa(Long id) {

		Empresa empresa = empresaService.buscarUM(id);
		List<Pedido> pedidos = repository.findByEmpresa(empresa);

		for (Pedido pedido : pedidos) {
			pedido.calcularTotal();
		}
		return pedidos;
	}

	public List<Pedido> buscarTodos() {
		List<Pedido> pedidos = repository.findAll();
		for (Pedido pedido : pedidos) {
			pedido.calcularTotal();
		}
		return pedidos;
	}

	public Pedido criar(PedidoDTO pedidoDTO) {

		Empresa empresa = empresaService.buscarUM(pedidoDTO.getEmpresaID());

		Pedido pedido = new Pedido(empresa);

		for (ItemPedidoDTO i : pedidoDTO.getItemPedido()) {
			if (!produtoService.verificarEstoque(i.getProdutoID(), i.getQuantidade())) {
				return null;
			}
		}
		pedido = repository.save(pedido);

		itemPedidoService.criarVarios(pedidoDTO.getItemPedido(), pedido);

		return pedido;
	}

	public void apagar(Long id) {
		repository.deleteById(id);
	}

	public Pedido atualizar(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		Pedido pedido = obj.get();
		pedido.setStatusPagamento(StatusPagamento.RETIRADO);
		pedido.getAgenda().setRetirado(true);
		return pedido = repository.save(pedido);
	}

}
