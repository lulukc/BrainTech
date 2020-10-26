package br.com.fiap.Brain_Tech.model.DTO;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long empresaID;
	private Set<ItemPedidoDTO> itemPedido;

}
