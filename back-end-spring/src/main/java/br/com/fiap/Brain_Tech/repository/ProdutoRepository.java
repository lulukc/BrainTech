package br.com.fiap.Brain_Tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Brain_Tech.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
