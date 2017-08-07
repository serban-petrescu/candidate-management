package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by oana on 4/20/17.
 */

@Data

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Education {
    private @Id
    @GeneratedValue
    Long id;
    private String educationType; //high-school, requalification, bachelor, master, phd
    private String provider; //UTCN, UBB, Informal School
    private String description; //Mathematics-Informatics
    private int duration;

    public Education() {
    }

    public Education(String educationType, String provider, String description, int duration) {
        this.educationType = educationType;
        this.provider = provider;
        this.description = description;
        this.duration = duration;
    }

}
