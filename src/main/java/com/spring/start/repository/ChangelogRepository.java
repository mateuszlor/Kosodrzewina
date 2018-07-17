package com.spring.start.repository;

import com.spring.start.entity.Changelog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangelogRepository extends CrudRepository<Changelog, Long>, ChangelogRepositoryAdditional {

	Changelog findByVersion(String version);

}
