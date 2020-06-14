package com.dathanwong.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dathanwong.dojooverflow.models.Question;
import com.dathanwong.dojooverflow.repositories.QuestionRepository;

@Service
public class QuestionService {
	
	private final QuestionRepository repo;
	
	public QuestionService(QuestionRepository repo) {
		this.repo = repo;
	}
	
	public List<Question> findAll(){
		return repo.findAll();
	}
	
	public Question findById(Long id) {
		Optional<Question> question = repo.findById(id);
		if(question.isPresent()) {
			return question.get();
		}else {
			return null;
		}
	}
	
	public void create(Question q) {
		repo.save(q);
	}
	
	public void update(Question q) {
		repo.save(q);
	}
}
