package com.zenfreela.zenid.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users_followers")
public class Follow extends AbstractModel {

    private String email;

    private String follower;

}