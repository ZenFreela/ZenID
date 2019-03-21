package com.zenfreela.zenid.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Document(collection = "users_profile")
@Getter @Setter
public class Profile {

    @Id
    private String email;

    private String firstName;

    private String lastName;

    private String cpf;

    private String biography;

    private String image;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

}