package com.spring.start;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

public class CustomNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	private static final String FOREIGN_KEY_PREFIX = "FK";
	private static final String CONNECTOR = "_";

	@Override
	public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source) {
		return toIdentifier(createForeignKeyName(source), source.getBuildingContext());
	}

	private String createForeignKeyName(ImplicitForeignKeyNameSource source) {
		return FOREIGN_KEY_PREFIX + CONNECTOR + source.getTableName().getCanonicalName() + CONNECTOR +
				source.getReferencedTableName().getCanonicalName() + CONNECTOR + source.getColumnNames().get(0).getCanonicalName();
	}

}
