package com.SimpleSpringReactProject.Demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SimpleSpringReactProject.Demo.domain.Project;
import com.SimpleSpringReactProject.Demo.services.MapValidationErrorService;
import com.SimpleSpringReactProject.Demo.services.ProjectService;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
	ResponseEntity<?> errorMap=	mapErrorService.mapValidationService(result);
	if(errorMap!=null) return errorMap;
	Project project1=	projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId ){
	Project project=projectService.findByProjectIdentifier(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProject(){
		return projectService.findAllProject();
	}
	
	@GetMapping("/{deleteById}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId)
	{
		projectService.deleteProjectById(projectId);
		return new ResponseEntity<String>("Project with ID "+ projectId + " was deleted",HttpStatus.OK);
	}

}
