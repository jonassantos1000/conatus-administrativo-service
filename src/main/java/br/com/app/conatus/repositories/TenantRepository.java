package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.conatus.entities.TenantEntity;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {
	
	boolean existsByPessoaJuridicaCnpj(String cnpj);

}
