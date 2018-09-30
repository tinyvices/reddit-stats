package io.tinyvices.redditstats;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String redditId;
    private String author;
    private String parentId;
    private int score;
    private int upvotes;
    private int likes;
    private int downvotes;
    private String body;
    private int controversiality;
    private Date created;
    private int gilded;


}
