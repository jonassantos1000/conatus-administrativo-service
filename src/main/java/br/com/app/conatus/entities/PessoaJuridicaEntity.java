package br.com.app.conatus.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PESSOA_JURIDICA")
@PrimaryKeyJoinColumn(name="ID_PESSOA")
@Builder @Setter @Getter 
@AllArgsConstructor @NoArgsConstructor
public class PessoaJuridicaEntity extends PessoaEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CD_CNPJ")
	private String cnpj;
	
}
