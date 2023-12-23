package com.example.restalfabank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "box")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Box {

    @Id
    private Long id;

    @Column(name = "contained_in")
    private Long containedIn;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "box")
    private Set<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    private Box parentBox;

    @OneToMany(mappedBy = "parentBox")
    private Set<Box> subBoxes;

    public Box(Long id, Long containedIn) {
        this.id = id;
        this.containedIn = containedIn;
    }
}
