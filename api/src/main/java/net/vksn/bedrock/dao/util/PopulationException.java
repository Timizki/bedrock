package net.vksn.bedrock.dao.util;

import net.vksn.bedrock.model.Entity;

public class PopulationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<? extends Entity> modelClass;
	
	public PopulationException() {
		super("Could not populate criteria");
	}
	
	@Deprecated
	public PopulationException(String message) {
		super(message);
	}
	
	public PopulationException(Class<? extends Entity> modelClass, String message) {			
		super(message);
		this.modelClass = modelClass;		
	}
	
	public Class<? extends Entity> getModelClass() {
		return this.modelClass;
	}
}
