package br.com.app.conatus.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_MODULO")
@Builder @AllArgsConstructor @NoArgsConstructor
@Setter @Getter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ModuloEntity {
	
	@Id @Include
	@Column(name = "IDENT")
	private Long id;
	
	@Column(name = "DS_MODULO")
	private String descricao;
	
	@Column(name = "VL_BASE")
	private BigDecimal valorBase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOM_SITUACAO")
	private DominioEntity situacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOM_TIPO")
	private DominioEntity tipoModulo;
	
	@UpdateTimestamp
	@Column(name = "DT_ATUALIZACAO")
	private ZonedDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "modulo")
	private List<FuncionalidadeEntity> funcionalidades;

}
