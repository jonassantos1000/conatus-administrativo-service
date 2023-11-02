package br.com.app.conatus.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "TB_MOVIMENTACAO")
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder @Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class MovimentacaoEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @Include 
	@Column(name = "IDENT") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "QT_MOVIM")
	private Integer quantidadeMovimentacao;
	
	@Column(name = "VL_UNIT")
	private BigDecimal valorUnitario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOM_TIPO")
	private DominioEntity tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TRANSACAO", referencedColumnName = "IDENT")
	private TransacaoEntity transacao;
	
	@CreationTimestamp
	@Column(name = "DT_MOVIMENTACAO")
	private ZonedDateTime dataMovimentacao;
	
	@Column(name = "VL_MOVIMENTACAO")
	private BigDecimal valorMovimentacao;
	
}
