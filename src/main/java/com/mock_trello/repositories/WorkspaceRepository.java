package com.mock_trello.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mock_trello.models.Workspace;

public interface WorkspaceRepository extends CrudRepository <Workspace, byte[]>{

	List<Workspace> findByAccountUserId(byte[] id);

}
