package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.conatus.commons.entities.MovimentacaoModuloEntity;

@Repository
public interface MovimentacaoModuloRepository extends JpaRepository<MovimentacaoModuloEntity, Long>{

}
