package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.SourceGenerator;
import app.repository.SourceGeneratorRepository;

@Service
@Transactional
public class SourceGeneratorService {

	@Autowired
	private SourceGeneratorRepository sourceGeneratorRepository;

	public List<SourceGenerator> findAll() {
		return sourceGeneratorRepository.findAll();
	}

	public Optional<SourceGenerator> findById(int id) {
		return sourceGeneratorRepository.findById(id);
	}

	public SourceGenerator getOne(int id) {
		return sourceGeneratorRepository.getOne(id);
	}
	
	public boolean existsById(int id) {
		return sourceGeneratorRepository.existsById(id);
	}

	public SourceGenerator save(SourceGenerator sourceGenerator) {
		return sourceGeneratorRepository.save(sourceGenerator);
	}

	public void deleteById(int id) {
		sourceGeneratorRepository.deleteById(id);
	}

}
