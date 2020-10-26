package br.com.fiap.Brain_Tech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.fiap.Brain_Tech.model.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer statusPagamento;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@Transient
	private Double total = 0.0;

	@NotNull
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ItemPedido> itemPedidos = new ArrayList<>();

	@JsonIgnore
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Agenda agenda;

	public Pedido(Empresa empresa) {
		this.empresa = empresa;
		setStatusPagamento(StatusPagamento.AGURDANDO_PAGAMENTO);
	}

	public void calcularTotal() {
		for (ItemPedido i : this.itemPedidos) {
			this.total += i.getSubTotal();
		}
	}

	public StatusPagamento getPerfil() {
		return StatusPagamento.valueOf(statusPagamento);
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		if (statusPagamento != null) {
			this.statusPagamento = statusPagamento.getCode();
		}
	}

	public void addItemPedido(ItemPedido itemPedido) {
		itemPedidos.add(itemPedido);
	}
}
