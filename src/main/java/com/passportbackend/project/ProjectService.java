package com.passportbackend.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(Project newProject) {
        return projectRepository.save(newProject);
    }

    public Project findProject(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }

    public Project updateProject(Long id, Project newProject) {
        return projectRepository.findById(id).map(project -> {
            newProject.setId(project.getId());
            return projectRepository.save(newProject);
        }).orElseThrow();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
