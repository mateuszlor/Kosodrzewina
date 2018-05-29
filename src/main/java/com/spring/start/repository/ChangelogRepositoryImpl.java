package com.spring.start.repository;

import com.spring.start.entity.Changelog;

import java.util.Comparator;

public class ChangelogRepositoryImpl extends BaseAdditionalRepositoryImpl<Changelog> implements ChangelogRepositoryAdditional{

	@Override
	public Changelog getLatestChangelog() {
		return getTable().max(Comparator.comparing(Changelog::getCreatedDate)).get();
	}

}
