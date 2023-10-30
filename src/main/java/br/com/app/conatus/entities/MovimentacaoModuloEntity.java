package br.com.app.conatus.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "TB_MOVIM_MODULO")
@PrimaryKeyJoinColumn(name="ID_MOVIMENTACAO")
@SuperBuilder @Setter @Getter 
@AllArgsConstructor @NoArgsConstructor
public class MovimentacaoModuloEntity extends MovimentacaoEntity {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TENANT_MODULO")
	private TenantModuloEntity tenantModulo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FUNCIONALIDADE")
	private TenantModuloFuncCustomEntity funcionalidadeCustom;
	
}
