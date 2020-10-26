package br.com.fiap.Brain_Tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.Produto;
import br.com.fiap.Brain_Tech.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto buscarUM(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.get();
	}

	public List<Produto> buscarTodos() {
		return repository.findAll();
	}

	public Produto criar(Produto empresa) {
		return repository.save(empresa);
	}

	public void apagar(Long id) {
		repository.deleteById(id);
	}

	public boolean verificarEstoque(Long id, Long quantidade) {
		Boolean resposta = false;

		Produto produto = buscarUM(id);

		if (produto.getEstoque().getQuantidade() > quantidade) {
			resposta = true;
		}

		return resposta;
	}

}
