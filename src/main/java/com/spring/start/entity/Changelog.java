package com.spring.start.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "changelog")
@NoArgsConstructor
@Getter
@Setter
public class Changelog extends BaseEntity<Changelog>{

	private String version;

	private String description;

	@Builder
	public Changelog(long id, Boolean deleted, Date createdDate, Date modificationDate, User creationUser, User modificationUser, String version, String description) {
		super(id, deleted, createdDate, modificationDate, creationUser, modificationUser);
		this.version = version;
		this.description = description;
	}

}
