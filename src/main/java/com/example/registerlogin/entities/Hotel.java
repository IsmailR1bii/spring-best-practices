package com.example.registerlogin.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = false)
    private String Name;
    @Column(nullable = false, unique = false)
    private String address;
    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;
}
