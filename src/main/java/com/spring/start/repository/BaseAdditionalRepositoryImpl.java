package com.spring.start.repository;

import com.spring.start.helper.JinqSource;
import lombok.experimental.var;
import org.jinq.jpa.JPAJinqStream;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Mateusz on 13.04.2017.
 */
@var
public class BaseAdditionalRepositoryImpl<T> {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    @SuppressWarnings("unchecked")
    public JPAJinqStream<T> getTable() {

        var type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return source.streamAll(em, type);
    }

    public <U> JPAJinqStream<U> getTable(Class<U> classToGet) {

        return source.streamAll(em, classToGet);
    }
}
