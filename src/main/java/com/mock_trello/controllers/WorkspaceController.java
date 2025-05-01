package com.mock_trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mock_trello.models.Workspace;
import com.mock_trello.services.WorkspaceService;
import com.mock_trello.utils.UUIDConverter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workspaces") // Base URL for this controller
public class WorkspaceController {
    @Autowired
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    // Create a new Workspace
    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        Workspace savedWorkspace = workspaceService.saveWorkspace(workspace);
        return ResponseEntity.ok(savedWorkspace);
    }

    // Retrieve a Workspace by ID
    @GetMapping("/{uuidString}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        Optional<Workspace> workspace = workspaceService.findWorkspaceById(id);
        return workspace.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all Workspaces
    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceService.findAllWorkspaces();
        return ResponseEntity.ok(workspaces);
    }

 // Retrieve Workspaces by AccountUser ID
    @GetMapping("/account-user/{uuidString}")
    public ResponseEntity<List<Workspace>> getWorkspacesByAccountUserId(@PathVariable String uuidString) {
        try {
            byte[] id = UUIDConverter.fromString(uuidString);
            List<Workspace> workspaces = workspaceService.findWorkspacesByAccountUserId(id);
            return ResponseEntity.ok(workspaces);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList()); // Return an empty Workspace list
        }
    }

    // Update a Workspace by ID
    @PutMapping("/{uuidString}")
    public ResponseEntity<Workspace> updateWorkspace(
            @PathVariable String uuidString,
            @RequestBody Workspace updatedWorkspace) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            Workspace workspace = workspaceService.updateWorkspace(id, updatedWorkspace);
            return ResponseEntity.ok(workspace);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Workspace by ID
    @DeleteMapping("/{uuidString}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            workspaceService.deleteWorkspace(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}