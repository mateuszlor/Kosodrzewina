package com.spring.start.repository;

import com.spring.start.entity.PeriodicService;
import com.spring.start.helper.JinqSource;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 03.04.2017.
 */
@var
public class PeriodicServiceRepositoryImpl implements PeriodicServiceRepositoryAdditional {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    public List<PeriodicService> getServicesSoonToExpire(int days) {

        var date = new Date();

        var cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        var dateToCheck = cal.getTime();

        return source.periodicServices(em)
                .filter(s -> s.getDateTo().before(dateToCheck)
                        && s.getDateTo().after(date))
                .collect(Collectors.toList());
    }
}
