package br.com.fiap.Brain_Tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Brain_Tech.model.Estoque;
import br.com.fiap.Brain_Tech.model.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	List<Estoque> findByProduto(Produto produto);

}
