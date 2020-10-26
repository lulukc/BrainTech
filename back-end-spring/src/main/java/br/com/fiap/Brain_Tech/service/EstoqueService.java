package br.com.fiap.Brain_Tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.Estoque;
import br.com.fiap.Brain_Tech.model.Produto;
import br.com.fiap.Brain_Tech.model.DTO.AtulizarEstoqueDTO;
import br.com.fiap.Brain_Tech.repository.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;

	@Autowired
	private ProdutoService produtoService;

	public Estoque buscarUM(Long id) {
		Optional<Estoque> obj = repository.findById(id);
		return obj.get();
	}

	public List<Estoque> buscarTodos() {
		return repository.findAll();
	}

	public Estoque criar(Estoque estoque) {
		return repository.save(estoque);
	}

	public void apagar(Long id) {
		repository.deleteById(id);
	}

	public Estoque adicionarEstoque(AtulizarEstoqueDTO atulizarEstoqueDTO) {
		Produto p = produtoService.buscarUM(atulizarEstoqueDTO.getProduto());
		p.getEstoque().adicionarQuantidate(atulizarEstoqueDTO.getQuantidade());
		return repository.save(p.getEstoque());
	}

//	public Estoque removerEstoque(List<ItemPedido> itemPedidos) {
//		for
//		Produto p = produtoService.buscarUM(atulizarEstoqueDTO.getProduto());
//		p.getEstoque().adicionarQuantidate(atulizarEstoqueDTO.getQuantidade());
//		return repository.save(p.getEstoque());
//	}
}
