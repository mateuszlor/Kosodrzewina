package com.spring.start.entity;

import com.spring.start.helper.ControllerHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Boolean deleted = false;

    @CreatedDate
    @NotNull
    @Column(name = "created_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedDate
    @NotNull
    @Column(name = "modification_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @CreatedBy
    @NotNull
    @ManyToOne
    @JoinColumn(name = "created_user")
    private User creationUser;

    @LastModifiedBy
    @NotNull
    @ManyToOne
    @JoinColumn(name = "modification_user")
    private User modificationUser;

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date();
        this.modificationDate = new Date();
        this.deleted = this.deleted == null ? false : this.deleted;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationDate = new Date();
    }

    @SuppressWarnings("unchecked")
    public static <T> T mergeUpdate(T basic, T edited) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = basic.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        List<Field> subFields = Arrays.asList(clazz.getSuperclass().getDeclaredFields());
        List<Field> finalFields = Stream.concat(fields.stream(), subFields.stream()).collect(Collectors.toList());
        Object returnValue = clazz.newInstance();
        for (Field field : finalFields) {
            field.setAccessible(true);
            Object value1 = field.get(basic);
            Object value2 = field.get(edited);
            Object value = (value1 != null && value2 != null && !value1.equals(value2) && !field.getName().equals("id")) ? value2 : value1;
            field.set(returnValue, value);
        }
        return (T) returnValue;
    }

}



