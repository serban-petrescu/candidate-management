package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Education entity
 * Has a type (e.g high-school), a provider(e.g. school name), a description and duration
 */
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Parsed
    private String educationType; //high-school, requalification, bachelor, master, phd

    @Parsed
    private String provider; //UTCN, UBB, Informal School

    @Parsed
    private String description; //Mathematics-Informatics

    @Parsed
    private int duration;

    public Education(String educationType, String provider, String description, int duration) {
        this.educationType = educationType;
        this.provider = provider;
        this.description = description;
        this.duration = duration;
    }

}
