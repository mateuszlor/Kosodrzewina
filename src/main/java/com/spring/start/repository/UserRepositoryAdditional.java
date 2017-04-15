package com.spring.start.repository;

import java.util.List;

/**
 * Created by Mateusz on 23.03.2017.
 */
public interface UserRepositoryAdditional {
    String getUserPassword(long id);
    List<String> getEmails();
}
