package com.spring.start;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

	private static final String CONNECTOR = "_";

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return new Identifier(createPhysicalName(name.getText()), name.isQuoted());
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		return new Identifier(createPhysicalName(name.getText()), name.isQuoted());
	}

	protected String createPhysicalName(String name) {
		StringBuilder builder = new StringBuilder();
		for (char character : name.toCharArray()) {
			if (Character.isUpperCase(character)) {
				builder.append(CONNECTOR);
			}
			builder.append(character);
		}
		return builder.toString().toLowerCase();
	}

}
