package br.com.app.conatus.entities.factory;

import java.math.BigDecimal;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.MovimentacaoEntity;
import br.com.app.conatus.commons.entities.TransacaoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovimentacaoEntityFactory {

	public static MovimentacaoEntity converterParaEntity(TransacaoEntity transacao, BigDecimal valorMovim, DominioEntity tipoMovim) {
		
		transacao.getValorTransacao().add(valorMovim);
		
		return MovimentacaoEntity.builder()
				.transacao(transacao)
				.valorMovimentacao(valorMovim)
				.valorUnitario(valorMovim)
				.quantidadeMovimentacao(1)
				.tipo(tipoMovim)
				.build();
	}
}
