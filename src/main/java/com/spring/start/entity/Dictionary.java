package com.spring.start.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vertig0 on 27.03.2017.
 */
@Entity
@Table(name = "dictionary")
@NoArgsConstructor
public class Dictionary extends BaseEntity<Dictionary>{

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "type", nullable = false)
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private DictionaryType type;

    @Builder
    public Dictionary(long id, Boolean deleted, Date createdDate, Date modificationDate, User creationUser, User modificationUser, String name, DictionaryType type) {
        super(id, deleted, createdDate, modificationDate, creationUser, modificationUser);
        this.name = name;
        this.type = type;
    }
}
