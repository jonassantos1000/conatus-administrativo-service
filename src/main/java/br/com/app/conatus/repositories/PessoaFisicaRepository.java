package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.conatus.commons.entities.PessoaFisicaEntity;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long>{

	boolean existsByCpf(String cpf);

}
