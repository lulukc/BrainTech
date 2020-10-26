package br.com.fiap.Brain_Tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Brain_Tech.model.Empresa;
import br.com.fiap.Brain_Tech.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByStatusPagamento(Integer statusPagamento);

	List<Pedido> findByEmpresa(Empresa empresa);

}
