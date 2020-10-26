package br.com.fiap.Brain_Tech.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	@Lob
	private String descrição;

	@NotNull
	private Double precoUnitario;

	@JsonIgnore
	@OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
	private Estoque estoque;

	@JsonIgnore
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@OneToMany(mappedBy = "produto")
	private Set<ItemPedido> itensPedidos = new HashSet<>();

	public Produto(Long id, String nome, String descrição, Double precoUnitario) {
		this.id = id;
		this.nome = nome;
		this.descrição = descrição;
		this.precoUnitario = precoUnitario;
	}

}
