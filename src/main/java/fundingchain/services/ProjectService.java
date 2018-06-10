package fundingchain.services;

import fundingchain.models.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    List<Project> findLatest5();
    Project findById(Long id);
    Project create(Project project);
    Project edit(Project project);
    void deleteById(Long id);
}