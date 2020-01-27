package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Source;
import app.repository.SourceRepository;

@Service
@Transactional
public class SourceService {

	@Autowired
	private SourceRepository sourceRepository;

	public List<Source> findAll() {
		return sourceRepository.findAll();
	}

	public Optional<Source> findById(int id) {
		return sourceRepository.findById(id);
	}

	public Source getOne(int id) {
		return sourceRepository.getOne(id);
	}

	public boolean existsById(int id) {
		return sourceRepository.existsById(id);
	}

	public Source save(Source source) {
		return sourceRepository.save(source);
	}

	public void deleteById(int id) {
		sourceRepository.deleteById(id);
	}
}
