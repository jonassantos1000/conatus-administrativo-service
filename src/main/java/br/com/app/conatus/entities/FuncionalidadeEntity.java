package br.com.app.conatus.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_FUNCIONALIDADE")
@Builder @AllArgsConstructor @NoArgsConstructor
@Setter @Getter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FuncionalidadeEntity {

	@Id @Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	private Long id;
	
	@Column(name = "DS_DESCRICAO")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOM_MODULO")
	private DominioEntity modulo;
		
	@ManyToOne
	@JoinColumn(name = "ID_DOM_TIPO")
	private DominioEntity tipoFuncionalidade;
	
	@JoinColumn(name = "VL_PRECO")
	private BigDecimal valorFuncionalidade;
	
}
