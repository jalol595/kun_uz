package com.company.kun_uz.entity;


import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.enums.ProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status=ProfileStatus.ACTIVE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRole role=ProfileRole.ADMIN;

    @Column(nullable = false)
    private Boolean visible=Boolean.TRUE;

    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();

}
