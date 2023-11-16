package br.com.app.conatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.conatus.commons.entities.TenantModuloFuncCustomEntity;

@Repository
public interface FuncionalidadeCustomizadaRepository extends JpaRepository<TenantModuloFuncCustomEntity, Long>{

}
