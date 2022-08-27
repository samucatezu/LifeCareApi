package com.samucatezu.pimbackend.Model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private Long id;

    @OneToOne
    private AppUserDetails AppUserDetails;

    @CPF
    @Column(nullable = true)
    private String cpf;

    private String streetName;

    private Integer streetNumber;

    private String zipCode;

    private String city;

    private String uf;


}
