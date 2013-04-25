package net.vksn.bedrock.services.impl;

import java.util.Collection;

import net.vksn.bedrock.dao.GenericDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.query.Query;
import net.vksn.bedrock.services.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractGenericService<T extends Entity> implements GenericService<T> {

	public abstract GenericDAO<T> getDAO();
		
	@Transactional(readOnly = true)
	@Override
	public Collection<T> getByQuery(Query query) {
		return getDAO().getByQuery(query);
	}

	@Transactional
	@Override
	public void store(T entity) throws EntityNotFoundException {
		getDAO().store(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public T get(int id) throws EntityNotFoundException {
		return this.getDAO().get(id, null);
	}
	
	@Transactional
	@Override
	public void remove(int id) throws EntityNotFoundException {
		this.getDAO().remove(id);
	}

	@Transactional
	@Override
	public void delete(int id) throws EntityNotFoundException {
		this.getDAO().delete(id);
	}

	@Transactional
	@Override
	public void undelete(int id) throws EntityNotFoundException {
		this.getDAO().undelete(id);
	}
}
