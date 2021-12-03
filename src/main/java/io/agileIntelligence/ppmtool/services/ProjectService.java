package io.agileIntelligence.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileIntelligence.ppmtool.domain.Project;
import io.agileIntelligence.ppmtool.exceptions.ProjectIdException;
import io.agileIntelligence.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}
		catch (Exception e) {
			throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
		
		
	}
	public Project findProjectByIdentifier(String projectId) {
		
		Project project =projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		 
		if(project == null) {
			throw new ProjectIdException("Project ID'"+projectId+  "' doesn't exists");
		}
		
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	
	
	
	
}
