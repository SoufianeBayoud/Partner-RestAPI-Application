package com.B2Boost.RestAPIProblem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "partner_table")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotNull
    @Column(name = "Name")
    private String companyName;

    @NotNull
    @Column(name = "Reference", unique = true)
    private String ref;

    @NotNull
    @Column(name = "Locale")
    private Locale locale = Locale.FRENCH;

    @NotNull
    @Column(name = "ExpirationTime")
    private Date expires = new Date(2524608000000L);
}
