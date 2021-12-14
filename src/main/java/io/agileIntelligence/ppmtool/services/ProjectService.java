package io.agileIntelligence.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileIntelligence.ppmtool.domain.Backlog;
import io.agileIntelligence.ppmtool.domain.Project;
import io.agileIntelligence.ppmtool.exceptions.ProjectIdException;
import io.agileIntelligence.ppmtool.repositories.BackLogRepository;
import io.agileIntelligence.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BackLogRepository backLogRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId()==null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			
			if(project.getId()!=null) {
				project.setBacklog(backLogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
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
	
	
	public void deleteProjectByIdentifier(String projectid) {
		Project project = projectRepository.findByProjectIdentifier(projectid.toUpperCase());
		
		if(project ==null) {
			throw new ProjectIdException("Cannot Project with ID '" +projectid+ "'. This project does not exist ");
		}
		
		projectRepository.delete(project);
	}
	
	
}
