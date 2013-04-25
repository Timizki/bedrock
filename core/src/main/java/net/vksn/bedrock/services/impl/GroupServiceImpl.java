package net.vksn.bedrock.services.impl;

import java.util.Collection;

import net.vksn.bedrock.dao.GenericDAO;
import net.vksn.bedrock.dao.GroupDAO;
import net.vksn.bedrock.model.Group;
import net.vksn.bedrock.query.Query;
import net.vksn.bedrock.services.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupServiceImpl extends AbstractGenericService<Group> implements GroupService {

	@Autowired
	private GroupDAO dao;
	
	@Override
	public Collection<Group> getAllGroups() {
		Query query = new Query();
		return getByQuery(query);
	}

	@Override
	public GenericDAO<Group> getDAO() {
		return dao;
	}

}
