package com.dathanwong.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dathanwong.dojooverflow.models.Tag;
import com.dathanwong.dojooverflow.repositories.TagRepository;

@Service
public class TagService {

	private final TagRepository repo;
	
	public TagService(TagRepository repo) {
		this.repo = repo;
	}
	
	public List<Tag> findAll(){
		return repo.findAll();
	}
	
	public void create(Tag tag) {
		repo.save(tag);
	}
	
	public void update(Tag tag) {
		repo.save(tag);
	}
	
	public Tag findBySubject(String subject) {
		subject = subject.trim();
		Optional<Tag> t = repo.findBySubject(subject);
		if(t.isPresent()) {
			System.out.println("Found: " + subject);
			return t.get();
		}else {
			System.out.println("Did not find: " + subject);
			return null;
		}
				
	}
}
