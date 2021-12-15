package io.agileIntelligence.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import io.agileIntelligence.ppmtool.domain.Backlog;
import io.agileIntelligence.ppmtool.domain.ProjectTask;
import io.agileIntelligence.ppmtool.repositories.BackLogRepository;
import io.agileIntelligence.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BackLogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

		// PTs to be added to a specific project !=null, BL exists
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		// set backlog to pt
		projectTask.setBacklog(backlog);
		// we want out project sequence to be like this: IDPRO-1 IDPRO-2
		Integer BacklogSequence = backlog.getPTSequence();
		// Update the BL SEQUENCE
		BacklogSequence++;
		
		backlog.setPTSequence(BacklogSequence);

		// Add Sequence to Project Task
		projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);

		// INITIAL priority when priority null
		if (projectTask.getPriority() == null) { //In the future we need projectTAsk.getPriority() 
			projectTask.setPriority(3);
		}

		// INITIAL status when status is null
		if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO_DO");
		}

		return projectTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> findBacklogById(String id) {
		// TODO Auto-generated method stub
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}

}
