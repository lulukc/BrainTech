package br.com.fiap.Brain_Tech.model.enums;

public enum TipoProduto {

	SO(1, "LOCAL"), SR(2, "EXPORTACAO"), SA(3, "AMOSTRA");

	private Integer code;
	private String descricao;

	private TipoProduto(Integer code, String descricao) {
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

	public static TipoProduto valueOf(Integer code) {
		for (TipoProduto value : TipoProduto.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("invalide StatusPagamento code");

	}
}
