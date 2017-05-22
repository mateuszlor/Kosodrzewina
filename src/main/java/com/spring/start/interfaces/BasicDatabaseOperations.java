package com.spring.start.interfaces;

/**
 * Created by Vertig0 on 22.05.2017.
 */
public interface BasicDatabaseOperations<A> {

    void delete(long id);

    Iterable<A> findAll();

    A findById(long id);

}
