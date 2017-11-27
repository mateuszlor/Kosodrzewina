package com.spring.start.interfaces;

import java.util.List;

/**
 * Created by Vertig0 on 22.05.2017.
 */
public interface BasicDatabaseOperations<A> {

    void delete(long id);

    Iterable<A> findAll();

    A findById(long id);

    List<A> findAllActive();

}
