package br.com.app.conatus.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.ModuloEntity;
import br.com.app.conatus.infra.exceptions.NaoEncontradoException;
import br.com.app.conatus.model.factory.ModuloResponseFactory;
import br.com.app.conatus.model.response.ModuloRecordResponse;
import br.com.app.conatus.repositories.ModuloRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModuloService {
	
	private final ModuloRepository moduloRepository;
	
	public ModuloRecordResponse pesquisarModulosPorId(Long idModulo) {
		
		ModuloEntity modulo = moduloRepository.findById(idModulo)
				.orElseThrow(() -> new NaoEncontradoException(String.format("Não foi encontrado um modulo com id: %d", idModulo)));

	    return ModuloResponseFactory.converterEntityParaRecord(modulo);
	}
	
	public List<ModuloRecordResponse> pesquisarModulos() {
			
		List<ModuloEntity> modulos = moduloRepository.findAll();

	    return ModuloResponseFactory.converterListEntityParaListRecord(modulos)
	    		.stream().sorted(Comparator.comparingLong(ModuloRecordResponse::id))
	    		.toList();
	}

}
