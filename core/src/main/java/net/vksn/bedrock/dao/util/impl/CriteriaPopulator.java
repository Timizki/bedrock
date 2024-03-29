package net.vksn.bedrock.dao.util.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import net.vksn.bedrock.dao.annotations.Match;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.query.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;


@Component
public class CriteriaPopulator extends AbstractCriteriaPopulator {
	
	@SuppressWarnings("rawtypes")
	@Override	
	public Criteria populateCriteria(Criteria criteria, Query query) {
		Method[] queryMethods = query.getClass().getMethods();
		for (Method method : queryMethods) {
			String methodName = method.getName();
			if (methodName.startsWith("get")) {
				if (!isNullGetProperty(method, query)) {
					continue;
				}

				Class<?> returnType = method.getReturnType();
				String propertyName = getPropertyName(methodName);
				
				try {
					if(Entity.class.isAssignableFrom(returnType)) {
						criteria.add(Restrictions.conjunction().add(Restrictions.eq(returnType.getSimpleName()+"_id",((Entity)method.invoke(query, new Object[0])).getId())));
						criteria.setFetchMode(returnType.getName()+".id", FetchMode.JOIN);
					}
					
					if (methodName.equals("getId")) {
						criteria.add(Restrictions.idEq(method.invoke(query, new Object[0])));
					}
					else if (returnType.equals(String.class)) {												
						populateString(method, propertyName, query, criteria);
					} 
					else if (returnType.equals(Integer.class)) {
						criteria.add(Restrictions.eq(propertyName,
								method.invoke(query, new Object[0])));
					} 
					else if(returnType.equals(Boolean.class)) {
						criteria.add(Restrictions.eq(propertyName, method.invoke(query, new Object[0])));
					}
					
//					TODO:Must implement date range, so not before and not after 
//					query parameters aren't mixed with entity attributes.
//					Meaby some kind of annotatios which terminates entity attributes name to query
//					else if (returnType.equals(Date.class)) {
//						criteria.add(Restrictions.eq(propertyName,
//								method.invoke(query, null)));
//					} 
					
					else if (Collection.class.isAssignableFrom(returnType)) {
						criteria.add(Restrictions.in(propertyName,
								(Collection) method.invoke(query, new Object[0])));
					} 
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					continue;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		return criteria;
	}
	
	protected Criteria populateString(Method method, String propertyName, Query query, Criteria criteria) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Match match = method.getAnnotation(Match.class);
		MatchMode mode = MatchMode.ANYWHERE;
		if(match != null) {
			if("EXACT".equals(match.mode())) {
				mode = MatchMode.EXACT;
			}
			else if("END".equals(match.mode())) {
				mode = MatchMode.END;
			}
			else if("START".equals(match.mode())) {
				mode = MatchMode.START;
			}
		}
		
		criteria.add(Restrictions.ilike(propertyName,
				(String) method.invoke(query, new Object[0]),mode));
		return criteria;
	}

}
