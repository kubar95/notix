package com.kubar95.notix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subject extends BaseEntity implements OwnableEntity{
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(
            mappedBy = "subject",
            cascade = CascadeType.ALL
    )
    @OrderBy("title ASC")
    private List<Note> notes = new ArrayList<>();

}
