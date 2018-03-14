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

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.types.CandidateCheck;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    Iterable<Candidate> findAllByCheckCandidate(CandidateCheck candidateCheck);

    Candidate findByIdAndCheckCandidate(Long id, CandidateCheck candidateCheck);

    List<Candidate> findFirst10ByEducationProviderContaining(@Param("provider") String provider);

    List<Candidate> findFirst10ByOrderByDateOfAddingDesc();

    List<Candidate> findFirst10ByEducationDescriptionContaining(@Param("description") String description);

    List<Candidate> findFirst10ByEducationEducationTypeContaining(@Param("educationType") String educationType);
}
