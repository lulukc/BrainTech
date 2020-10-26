package br.com.fiap.Brain_Tech.model.enums;

public enum TipoPedido {

	SO(1, "LOCAL"), SR(2, "EXPORTACAO"), SA(3, "AMOSTRA");

	private Integer code;
	private String descricao;

	private TipoPedido(Integer code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	public int getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoPedido valueOf(Integer code) {
		for (TipoPedido value : TipoPedido.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("invalide StatusPagamento code");

	}
}
