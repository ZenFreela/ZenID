package com.zenfreela.zenid.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Builder
@NoArgsConstructor
@Getter @Setter
@Document(collection = "users_followers")
public class Follow {

    @Id
    private String email;

    @CreatedDate
    private Date createdDate;

}