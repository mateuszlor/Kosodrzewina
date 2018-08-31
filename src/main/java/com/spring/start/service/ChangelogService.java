package com.spring.start.service;

import com.spring.start.entity.Changelog;
import com.spring.start.interfaces.BasicDatabaseOperations;
import com.spring.start.repository.ChangelogRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@Log4j
public class ChangelogService implements BasicDatabaseOperations<Changelog> {

	@Autowired
	@Getter @Setter
	private ChangelogRepository changelogRepository;

	public Changelog getLatestVersionChangelog() {
		return changelogRepository.getLatestChangelog() != null ? changelogRepository.getLatestChangelog() : new Changelog();
	}

	public void saveChangelog(String version, String description) {

		Changelog changelog = getChangelogByVersion(version);
		if (changelog != null && !changelog.isEmpty()) {
			changelog.setDescription(description);
		} else {
			changelog = Changelog.builder()
					.version(version)
					.description(description)
					.build();
		}

		changelogRepository.save(changelog);
	}

	public Changelog getChangelogByVersion(String version) {
		return changelogRepository.findByVersion(version) != null ? changelogRepository.findByVersion(version) : new Changelog();
	}

	@Override
	public void delete(long id) {

	}

	@Override
	public Iterable<Changelog> findAll() {
		return null;
	}

	@Override
	public Changelog findById(long id) {
		return null;
	}

	@Override
	public List<Changelog> findAllActive() {
		return null;
	}
}
