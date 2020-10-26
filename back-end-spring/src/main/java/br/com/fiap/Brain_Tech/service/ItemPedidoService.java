package br.com.fiap.Brain_Tech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.ItemPedido;
import br.com.fiap.Brain_Tech.model.Pedido;
import br.com.fiap.Brain_Tech.model.Produto;
import br.com.fiap.Brain_Tech.model.DTO.ItemPedidoDTO;
import br.com.fiap.Brain_Tech.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	public ItemPedido buscarUM(Long id) {
		Optional<ItemPedido> obj = repository.findById(id);
		return obj.get();
	}

	public List<ItemPedido> buscarTodos() {
		return repository.findAll();
	}

	public List<ItemPedido> criarVarios(Set<ItemPedidoDTO> set, Pedido pedido) {
		List<ItemPedido> itemPedidos = new ArrayList<>();

		for (ItemPedidoDTO i : set) {
			Produto p = produtoService.buscarUM(i.getProdutoID());
			itemPedidos.add(new ItemPedido(pedido, p, i.getQuantidade(), i.getTipoPedido()));
		}
		repository.saveAll(itemPedidos);
		return itemPedidos;
	}

	public void apagar(Long id) {
		repository.deleteById(id);
	}

}
