package com.spring.start.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Vertig0 on 27.03.2017.
 */
@Entity
@Table(name = "dictionary")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dictionary extends BaseEntity<Dictionary>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Getter
    @Setter
    private long id;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "type", nullable = false)
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private DictionaryType type;

}
