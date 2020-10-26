package br.com.fiap.Brain_Tech.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.Brain_Tech.model.enums.TipoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@JsonIgnore
	private Pedido pedido;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	private Long quantidade;

	private Double subTotal;

	private Integer tipoPedido;

	public ItemPedido(Pedido pedido, Produto produto, Long quantidade, Integer tipoPedido) {
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.tipoPedido = tipoPedido;
		calculaSubTotal();

	}

	public void calculaSubTotal() {
		this.subTotal = quantidade * produto.getPrecoUnitario();
	}

	public TipoPedido getTipoPedido() {
		return TipoPedido.valueOf(tipoPedido);
	}

	public void setTipoPedido(TipoPedido tipoPedido) {
		if (tipoPedido != null) {
			this.tipoPedido = tipoPedido.getCode();
		}
	}

}
