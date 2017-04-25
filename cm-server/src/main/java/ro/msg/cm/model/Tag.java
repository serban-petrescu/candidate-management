package ro.msg.cm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * Created by oana on 4/20/17.
 */

@Data
@Getter
@Setter
@Entity
public class Tag {

    private @Id @GeneratedValue Long id;
    private String description; // German, French, Java, Javascript, SQL
    private String tagType; //Foreign, Programming Languages

    private Tag() {}

    public Tag(String description, String tagType) {
        this.description = description;
        this.tagType = tagType;
    }

}
