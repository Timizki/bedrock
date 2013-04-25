package net.vksn.bedrock.services;

import java.util.Collection;

import net.vksn.bedrock.model.Group;

public interface GroupService extends GenericService<Group> {

	Collection<Group> getAllGroups();
}
