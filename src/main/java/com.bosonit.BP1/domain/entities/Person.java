package com.bosonit.BP1.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;

    @Column(name="User_name")
    private String username;

    //    @NonNull
    @Column(name="Password")
    private String password;

    //    @NonNull
    @Column(name="Name")
    private String name;

    @Column(name="Surname")
    private String surname;

    //    @NonNull
    @Column(name="Company_Email")
    private String company_email;

    //    @NonNull
    @Column(name="Personal_Email")
    private String personal_email;

    //    @NonNull
    @Column(name="City")
    private String city;

    //    @NonNull
    @Column(name="Active")
    private Boolean active;

    //    @NonNull
    @Column(name="Created_Date")
    private Date created_date;

    @Column(name="Image_url")
    private String image_url;

    @Column(name="Termination_Date")
    private Date termination_date;

    @Column(name="Admin")
    private Boolean admin;

    @OneToOne(mappedBy = "person")
    private Student student;

    @OneToOne(mappedBy = "person")
    private Professor professor;


}
