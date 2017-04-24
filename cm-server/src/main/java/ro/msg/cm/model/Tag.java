package ro.msg.cm.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * Created by oana on 4/20/17.
 */

@Data
@Entity
public class Tag {

    private @Id @GeneratedValue Long id;
    private String description;
    private String tagType;
    private String certifer;

    private Tag() {}

    public Tag(String description, String tagType, String rating, String certifer) {
        this.description = description;
        this.tagType = tagType;
        this.certifer = certifer;
    }

}
