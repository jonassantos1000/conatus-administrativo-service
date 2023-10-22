package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.conatus.entities.FuncionalidadeCustomizadaEntity;

@Repository
public interface FuncionalidadeCustomizadaRepository extends JpaRepository<FuncionalidadeCustomizadaEntity, Long>{

}
