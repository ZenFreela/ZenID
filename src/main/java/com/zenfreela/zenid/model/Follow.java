package com.zenfreela.zenid.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document(collection = "users_followers")
public class Follow extends AbstractModel {

    private String email;

    private String follower;

}