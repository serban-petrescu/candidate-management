package ro.msg.cm.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.CandidateNotValidated;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CandidateNotValidatedRepository extends CrudRepository<CandidateNotValidated, Long> {

    CandidateNotValidated save(CandidateNotValidated persisted);

    List<CandidateNotValidated> findAll();

    CandidateNotValidated findOne(Long id);

    void delete(Long id);

    @Query(value = "SELECT * FROM candidate_not_validated t WHERE t.last_name =(?1)", nativeQuery = true)
    CandidateNotValidated findByLastName(String lastName);

    @Modifying
    @Query(value = "INSERT INTO candidate_not_validated (last_name, original_study_year) VALUES (?1,?2)", nativeQuery = true)
    void insertIntoCandidateNotValidated(String last_name, int original_study_year);

    @Modifying
    @Query(value = "INSERT INTO candidate(date_of_adding,education_status,email,first_name, last_name,original_study_year,phone,education_id) " +
            " SELECT date_of_adding,education_status,email,first_name, last_name,original_study_year,phone,education_id FROM candidate_not_validated", nativeQuery = true)
    void insertAll();

    @Modifying
    @Query(value = "INSERT INTO candidate(date_of_adding,education_status,email,first_name, last_name,original_study_year,phone,education_id) " +
            " SELECT date_of_adding,education_status,email,first_name, last_name,original_study_year,phone,education_id FROM candidate_not_validated WHERE id=(?1)",
            nativeQuery = true)
    void insertById(Long id);

    @Modifying
    @Query(value = "INSERT INTO candidate(date_of_adding,education_status,email,first_name, last_name,original_study_year,phone,education_id) " +
            " SELECT date_of_adding,education_status,email,first_name, last_name,original_study_year,phone,education_id FROM candidate_not_validated WHERE id IN (?1)",
            nativeQuery = true)
    void insertByListOfIds(List<Long> listOfIds);
}
