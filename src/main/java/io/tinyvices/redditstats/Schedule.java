package io.tinyvices.redditstats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String text;
    private String repeat;
    private boolean sticky;
    private boolean distinguish;
    private String first;
    private String title;

    @JsonIgnore
    private String subreddit;
}
