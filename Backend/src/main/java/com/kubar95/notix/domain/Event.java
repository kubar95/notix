package com.kubar95.notix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event extends BaseEntity implements OwnableEntity{

    private String title;
    private String description;
    private Timestamp start;
    private Timestamp end;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}
