package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import ro.msg.cm.types.TagType;

import javax.persistence.*;

/**
 * Tag entity
 * Has a description and a type(e.g. programming languages)
 */

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Parsed
    private String description; // German, French, Java, Javascript, SQL

    @Parsed
    @Enumerated(EnumType.STRING)
    private TagType tagType; //FOREIGN_LANGUAGE, PROGRAMMING_LANGUAGE or OTHER
}
