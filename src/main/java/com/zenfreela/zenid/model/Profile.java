package com.zenfreela.zenid.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document(collection = "users_profile")
public class Profile extends AbstractModel {

    private String email;

    private String firstName;

    private String lastName;

    private String cpf;

    private String biography;

    private String image;

}