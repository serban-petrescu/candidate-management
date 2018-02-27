/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.msg.cm.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.types.CandidateCheck;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    List<Candidate> findAllByCheckCandidate(CandidateCheck candidateCheck);

    Set<Candidate> findAllByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(String firstName, String lastName, CandidateCheck candidateCheck, long id);

    Set<Candidate> findAllByEmailAndCheckCandidateAndIdIsNot(String email, CandidateCheck candidateCheck, long id);

    Set<Candidate> findAllByPhoneAndCheckCandidateAndIdIsNot(String phone, CandidateCheck candidateCheck, long id);

    Long countByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(String firstName, String lastName, CandidateCheck candidateCheck, long id);

    Long countByEmailAndCheckCandidateAndIdIsNot(String email, CandidateCheck candidateCheck, long id);

    Long countByPhoneAndCheckCandidateAndIdIsNot(String phone, CandidateCheck candidateCheck, long id);

    @Transactional
    @Modifying
    @Query("update Candidate c set c.checkCandidate = ?1 where c.id in ?2")
    void setCheckCandidateForIdIn(CandidateCheck candidateCheck, List<Long> ids);

    @Transactional
    @Modifying
    @Query("update Candidate c set c.checkCandidate = ?1 where c.id = ?2")
    void setCheckCandidateForId(CandidateCheck candidateCheck, Long id);

    Candidate findByIdAndCheckCandidate(Long id, CandidateCheck check);

    Optional<Candidate> findCandidateById(Long id);

    List<Candidate> save(List<Candidate> candidates);
}
