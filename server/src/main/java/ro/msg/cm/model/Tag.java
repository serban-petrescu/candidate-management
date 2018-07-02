package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Tag entity
 * Has a description and a type(e.g. programming languages)
 */

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Parsed
    private String description; // German, French, Java, Javascript, SQL

    @Parsed
    private String tagType; //Foreign, Programming Languages
}
