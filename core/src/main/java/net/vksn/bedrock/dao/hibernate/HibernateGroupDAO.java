package net.vksn.bedrock.dao.hibernate;

import org.springframework.stereotype.Component;

import net.vksn.bedrock.dao.GroupDAO;
import net.vksn.bedrock.model.Group;

@Component
public class HibernateGroupDAO extends AbstractHibernateDAO<Group> implements GroupDAO {

	public HibernateGroupDAO() {
		super(Group.class);
	}
}
