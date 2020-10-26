package br.com.fiap.Brain_Tech.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.fiap.Brain_Tech.model.ItemPedido;
import br.com.fiap.Brain_Tech.model.Pedido;
import br.com.fiap.Brain_Tech.model.enums.StatusPagamento;
import br.com.fiap.Brain_Tech.repository.PedidoRepository;

@Component
public class PagamendoSchedule {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Scheduled(cron = "0 * * * * *")
	public void atulizarPagamento() {
		List<Pedido> pedidos = pedidoRepository.findByStatusPagamento(1);

		for (Pedido p : pedidos) {
			p.setStatusPagamento(StatusPagamento.PAGO);
			for (ItemPedido it : p.getItemPedidos()) {
				it.getProduto().getEstoque().removerQuantidate(it.getQuantidade());
				;

			}
		}

		pedidoRepository.saveAll(pedidos);
	}

}
