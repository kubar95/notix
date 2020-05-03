package com.kubar95.notix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Note extends BaseEntity implements OwnableEntity{

    private String title;
    private Timestamp date;
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Override
    public User getOwner() {
        return subject.getOwner();
    }
}
