package br.com.app.conatus.entities;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TenantId;
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

@Entity
@Table(name = "TB_TENANT_MODULO")
@Builder @AllArgsConstructor @NoArgsConstructor
@Setter @Getter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TenantModuloEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENT")
	private Long id;

	@JoinColumn(name = "FL_POSSUI_CUSTOM")
	private Boolean flagPossuiCustom;
	
	@TenantId
	@Column(name = "ID_TENANT", insertable = false, updatable = false)
	private String idTenant;
	
	@ManyToOne
	@JoinColumn(name = "ID_TENANT")
	private TenantEntity tenant;
	
	@ManyToOne
	@JoinColumn(name = "ID_MODULO")
	private ModuloEntity modulo;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOM_SITUACAO")
	private DominioEntity situacao;
	
	@Column(name = "DT_EXPIRACAO")
	private ZonedDateTime dataExpiracao;
	
	@CreationTimestamp
	@Column(name = "DT_CADASTRO")
	private ZonedDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "DT_ATUALIZACAO")
	private ZonedDateTime dataAtualizacao;
	
}
