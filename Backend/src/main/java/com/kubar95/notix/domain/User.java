package com.kubar95.notix.domain;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;

    @OneToMany (
            mappedBy = "owner",
            cascade = CascadeType.ALL
    )
    @OrderBy("name ASC")
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany (
            mappedBy = "owner",
            cascade = CascadeType.ALL
    )
    @OrderBy("start ASC")
    private List<Event> events = new ArrayList<>();


    public User addSubject(Subject subject) {
        subject.setOwner(this);
        this.subjects.add(subject);
        return this;
    }
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.setOwner(null);
    }
}
