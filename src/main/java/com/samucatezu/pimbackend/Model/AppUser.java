package com.samucatezu.pimbackend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "appUser")
public class AppUser {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private Long id;



//    @CPF
    @Column(nullable = true)
    private String cpf;

    private String streetName;

    private Integer streetNumber;

    private String zipCode;

    private String city;

    private String uf;


}
