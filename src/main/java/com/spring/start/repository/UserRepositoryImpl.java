package com.spring.start.repository;

import com.spring.start.entity.User;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 23.03.2017.
 */
@var
@Log4j
@Repository()
public class UserRepositoryImpl extends BaseAdditionalRepositoryImpl<User> implements UserRepositoryAdditional {

    @Override
    public String getUserPassword(long id) {

        log.info("About to get password for user ID=" + id);

        // Issue a query
        var list = getTable()
                .where(u -> u.getId() == id)
                .select(u -> u.getPassword())
                .limit(1)
                .toList();

        log.info("User password = " + list.get(0));

        return list.get(0);
    }

    @Override
    public List<String> getEmails() {
        return getTable()
                .where(u -> u.isEnabled())
                .select(u -> u.getEmail())
                .distinct()
                .collect(Collectors.toList());
    }

}
