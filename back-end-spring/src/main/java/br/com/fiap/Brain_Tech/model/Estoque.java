package br.com.fiap.Brain_Tech.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_estoque")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private Long quantidadeReservada;

	@NotNull
	private Long quantidade;

	@NotNull
	@OneToOne()
	private Produto produto;

	public void adicionarQuantidate(Long quantidade) {
		this.quantidade += quantidade;
	}

	public void removerQuantidate(Long quantidade) {
		this.quantidade -= quantidade;
	}

}
