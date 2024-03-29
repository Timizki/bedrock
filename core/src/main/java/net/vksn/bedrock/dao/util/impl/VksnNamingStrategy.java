package net.vksn.bedrock.dao.util.impl;

import org.hibernate.cfg.ImprovedNamingStrategy;


public class VksnNamingStrategy extends ImprovedNamingStrategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        String s = super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName);
        return s.endsWith("_id") ? s : s + "_id";
    }

	@Override
	public String collectionTableName(String ownerEntity,
			String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		
		String collectionTableName = super.collectionTableName(ownerEntity, ownerEntityTable,
				associatedEntity, associatedEntityTable, propertyName);
		return  collectionTableName;
	}

	@Override
	public String classToTableName(String className) {

		return super.classToTableName(className);
	}
	
	
}
