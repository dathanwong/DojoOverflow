package com.dathanwong.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dathanwong.dojooverflow.models.Answer;
import com.dathanwong.dojooverflow.repositories.AnswerRepository;

@Service
public class AnswerService {

	private final AnswerRepository repo;
	
	public AnswerService(AnswerRepository repo) {
		this.repo = repo;
	}
	
	public List<Answer> findAll(){
		return repo.findAll();
	}
	
	public Answer findById(Long id) {
		Optional<Answer> answer = repo.findById(id);
		if(answer.isPresent()) {
			return answer.get();
		}else {
			return null;
		}
	}
	
	public void create(Answer a) {
		repo.save(a);
	}
	
	public void update(Answer a) {
		repo.save(a);
	}
}
