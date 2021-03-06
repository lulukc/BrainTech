package br.com.fiap.Brain_Tech.model.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtulizarEstoqueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long produto;
	private Long quantidade;

}
