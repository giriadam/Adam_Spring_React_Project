package com.SimpleSpringReactProject.Demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SimpleSpringReactProject.Demo.domain.Project;
import com.SimpleSpringReactProject.Demo.exceptions.ProjectIDException;
import com.SimpleSpringReactProject.Demo.repositories.ProjectRepositories;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepositories projectRepositories;
	
	public Project saveOrUpdate(Project project)
	{
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepositories.save(project);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProjectIDException("Project ID"+project.getProjectIdentifier().toUpperCase()+" Already Exist");
		}
	}
	
	public Project findByProjectIdentifier(String projectIdentifier) {
		
	Project project=projectRepositories.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if(project==null)
		{
			throw new ProjectIDException("Project ID "+projectIdentifier+" doesnt exist");
		}
		return project; 
	}

	public Iterable<Project> findAllProject() {
		return projectRepositories.findAll();
	}
	
	
	public void deleteProjectById(String Id)
	{
		Project project=projectRepositories.findByProjectIdentifier(Id.toUpperCase());
		if(project==null)
		{
			throw new ProjectIDException("Project ID "+Id+" doesnt exist");
		}
		projectRepositories.delete(project);
	
	}

}
