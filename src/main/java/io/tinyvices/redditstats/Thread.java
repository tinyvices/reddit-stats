package io.tinyvices.redditstats;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany( targetEntity = Comment.class)
    private List<Comment> comments;
    private final String redditId;
    private Date created;
    private String title;
    private String author;

}
