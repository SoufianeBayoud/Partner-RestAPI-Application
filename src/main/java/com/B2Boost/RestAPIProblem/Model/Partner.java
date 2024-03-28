package com.B2Boost.RestAPIProblem.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Partner_table")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String companyName;

    @Column(name = "Reference", unique = true)
    private String ref;

    @Column(name = "Locale")
    private Locale locale;

    @CreationTimestamp
    @Column(name = "ExpirationTime")
    private Date expires;

}
