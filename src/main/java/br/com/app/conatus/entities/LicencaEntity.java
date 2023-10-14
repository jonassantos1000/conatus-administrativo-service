package br.com.app.conatus.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.hibernate.annotations.UpdateTimestamp;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "TB_LICENCA")
@Builder @AllArgsConstructor @NoArgsConstructor
@Setter @Getter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LicencaEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOM_TIPO_LICENCA")
	private DominioEntity tipoLicenca;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOM_SITUACAO")
	private DominioEntity situacao;
	
	@Column(name = "DT_EXPIRACAO")
	private ZonedDateTime dataExpiracao;

	@UpdateTimestamp
	@Column(name = "DT_ATUALIZACAO")
	private ZonedDateTime dataAtualizacao;
}
