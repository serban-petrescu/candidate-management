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
package ro.msg.cm.dbloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.model.Education;
import ro.msg.cm.model.Tag;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.CandidateSkillsRepository;
import ro.msg.cm.repository.EducationRepository;
import ro.msg.cm.repository.TagRepository;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final CandidateRepository candidateRepository;
	private final TagRepository tagRepository;
	private final EducationRepository educationRepository;
	private final CandidateSkillsRepository candidateSkillsRepository;

	@Autowired
	public DatabaseLoader(CandidateRepository candidateRepository, TagRepository tagRepository, EducationRepository educationRepository, CandidateSkillsRepository candidateSkillsRepository) {
		this.candidateRepository = candidateRepository;
        this.tagRepository = tagRepository;
        this.educationRepository = educationRepository;
        this.candidateSkillsRepository = candidateSkillsRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		// clean previous data in the table
        /*Iterable<Candidate> cand = this.candidateRepository.findAll();
        Iterable<Education> edu = this.educationRepository.findAll();
        Iterable<Tag> tags = this.tagRepository.findAll();
        Iterable<CandidateSkills> all = this.candidateSkillsRepository.findAll();
        */this.candidateSkillsRepository.deleteAll();
        this.candidateRepository.deleteAll();
        this.educationRepository.deleteAll();
        this.tagRepository.deleteAll();

        this.educationRepository.save(new Education("High-School","Marie Curie","Informatics"));
        this.educationRepository.save(new Education("Bachelor","UBB","Mathematics-Informatics"));
        Education education  = new Education("Master", "UTCN","Information Technology");

        this.educationRepository.save(education);


		this.candidateRepository.save(new Candidate("Miralem", "Pjanic", "012986212","aaa@a.a"));
		this.candidateRepository.save(new Candidate("Sami", "Khedira"));
		this.candidateRepository.save(new Candidate("Mario", "Mandzukic"));
		this.candidateRepository.save(new Candidate("Paulo", "Dybala"));
		Candidate test = new Candidate("Alex", "Sandro");
        test.setEducation(education);
		this.candidateRepository.save(test);

		this.tagRepository.save(new Tag("German","foreign"));
		this.tagRepository.save(new Tag("English","foreign"));
		Tag trial = new Tag ("Java","programming");
		this.tagRepository.save(trial);
        this.candidateRepository.save(test);


        this.candidateSkillsRepository.save(new CandidateSkills(test,trial,"average"));


	}

}