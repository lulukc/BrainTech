package br.com.fiap.Brain_Tech.model.enums;

public enum StatusPagamento {

	AGURDANDO_PAGAMENTO(1), PAGO(2), RETIRADA_AGENDADA(3), RETIRADO(4), CANCELADO(5);

	private int code;

	private StatusPagamento(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static StatusPagamento valueOf(int code) {
		for (StatusPagamento value : StatusPagamento.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("invalide StatusPagamento code");

	}
}
