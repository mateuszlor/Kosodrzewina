package com.spring.start.repository;

import com.spring.start.helper.JinqSource;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Mateusz on 23.03.2017.
 */
@var
@Log4j
@Repository()
public class UserRepositoryImpl implements UserRepositoryAdditional {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    @Override
    public String getUserPassword(long id){

        log.info("About to get password for user ID=" + id);

        // Initialize Jinq
        JinqJPAStreamProvider streams = new JinqJPAStreamProvider(em.getMetamodel());

        // Issue a query
        var list = source.users(em)
                .where(u -> u.getId() == id)
                .select(u->u.getPassword())
                .limit(1)
                .toList();

        log.info("User password = " + list.get(0));

        return list.get(0);
    }

}