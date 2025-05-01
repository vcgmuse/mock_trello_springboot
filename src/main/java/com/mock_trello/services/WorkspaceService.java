package com.mock_trello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mock_trello.models.Workspace;
import com.mock_trello.repositories.WorkspaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {
    @Autowired
    private final WorkspaceRepository workspaceRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    // Create or Save a Workspace
    public Workspace saveWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    // Retrieve a Workspace by ID
    public Optional<Workspace> findWorkspaceById(byte[] id) {
        return workspaceRepository.findById(id);
    }

    // Retrieve all Workspaces
    public List<Workspace> findAllWorkspaces() {
        return (List<Workspace>) workspaceRepository.findAll(); // Cast to List for CrudRepository
    }

    // Retrieve Workspaces by User ID
    public List<Workspace> findWorkspacesByUserId(byte[] userId) {
        return workspaceRepository.findByAccountUserId(userId);
    }

    // Update an Existing Workspace
    public Workspace updateWorkspace(byte[] id, Workspace updatedWorkspace) {
        return workspaceRepository.findById(id)
            .map(workspace -> {
                workspace.setName(updatedWorkspace.getName());
                workspace.setAccountUser(updatedWorkspace.getAccountUser());
                return workspaceRepository.save(workspace);
            })
            .orElseThrow(() -> new RuntimeException("Workspace not found with ID: " + id));
    }

    // Delete a Workspace by ID
    public void deleteWorkspace(byte[] id) {
        if (workspaceRepository.existsById(id)) {
            workspaceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Workspace not found with ID: " + id);
        }
    }
    public List<Workspace> findWorkspacesByAccountUserId(byte[] id) {
        // Query the repository to find workspaces by the account user ID
        return workspaceRepository.findByAccountUserId(id);
    }
}