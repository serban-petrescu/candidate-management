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
 * Education entity
 * Has a type (e.g high-school), a provider(e.g. school name), a description and duration
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
    @Parsed
    private String educationType; //high-school, requalification, bachelor, master, phd
    @Parsed
    private String provider; //UTCN, UBB, Informal School
    @Parsed
    private String description; //Mathematics-Informatics
    @Parsed
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
