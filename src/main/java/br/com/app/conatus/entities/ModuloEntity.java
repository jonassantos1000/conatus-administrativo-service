package br.com.app.conatus.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "TB_MODULO")
@PrimaryKeyJoinColumn(name="ID_SERVICO")
@SuperBuilder @Setter @Getter 
@AllArgsConstructor @NoArgsConstructor
public class ModuloEntity extends ServicoEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "NM_MODULO")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOM_MODULO")
	private DominioEntity tipoModulo;
	
	@OneToMany(mappedBy = "modulo")
	private List<FuncionalidadeEntity> funcionalidades;
	
	@OneToMany(mappedBy = "modulo")
	private List<ModuloSubModuloEntity> moduloSubModulos;

}
