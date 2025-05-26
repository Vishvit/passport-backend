package com.passportbackend.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/projects")
    public List<Project> findAllProjects() {
        return projectService.findAllProjects();
    }

    @PostMapping(value = "/projects")
    public Project saveProject(@RequestBody Project newProject) {
        return projectService.saveProject(newProject);
    }

    @GetMapping(value = "/projects/{id}")
    public Project findProject(@PathVariable Long id) {
        return projectService.findProject(id);
    }

    @PutMapping(value = "/projects/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project newProject) {
        return projectService.updateProject(id, newProject);
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}
