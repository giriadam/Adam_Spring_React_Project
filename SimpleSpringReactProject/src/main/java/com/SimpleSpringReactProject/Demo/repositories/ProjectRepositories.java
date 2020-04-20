package com.SimpleSpringReactProject.Demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SimpleSpringReactProject.Demo.domain.Project;

@Repository
public interface ProjectRepositories extends CrudRepository<Project, Long> {

	
	
	
	Project findByProjectIdentifier(String projectIdentifier);
}
