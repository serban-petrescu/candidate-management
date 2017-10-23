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
import ro.msg.cm.model.*;
import ro.msg.cm.repository.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CandidateRepository candidateRepository;

    private final TagRepository tagRepository;

    private final EducationRepository educationRepository;

    private final CandidateSkillsRepository candidateSkillsRepository;

    private final CandidateNotesRepository candidateNotesRepository;

    @Autowired
    public DatabaseLoader(CandidateRepository candidateRepository, TagRepository tagRepository, EducationRepository educationRepository,
                          CandidateSkillsRepository candidateSkillsRepository, CandidateNotesRepository candidateNotesRepository) {
        this.candidateRepository = candidateRepository;
        this.tagRepository = tagRepository;
        this.educationRepository = educationRepository;
        this.candidateSkillsRepository = candidateSkillsRepository;
        this.candidateNotesRepository = candidateNotesRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // loadData();
    }

    private void loadData() throws Exception {
        if (!isDatabaseEmpty()) {
            emptyDatabase();
        }
        loadEducation();
        loadFromMockDataCSV();
        Education education = new Education("Master", "UTCN", "Information Technology", 2);
        this.educationRepository.save(education);
    }

    private void emptyDatabase() {
        this.candidateSkillsRepository.deleteAll();
        this.candidateRepository.deleteAll();
        // this.educationRepository.deleteAll();
        this.tagRepository.deleteAll();

    }

    private void loadEducation() {
        this.educationRepository.save(new Education("High-School", "Marie Curie", "Informatics", 3));
        this.educationRepository.save(new Education("Bachelor", "UBB", "Mathematics-Informatics", 4));
        Education education = new Education("Master", "UTCN", "Information Technology", 2);
        this.educationRepository.save(education);
    }

    private void loadFromMockDataCSV() {

        String csvFile = "/MockDataCSV.csv";
        String line = "";
        String cvsSplitBy = ",";
        String header = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(DatabaseLoader.class
            .getResourceAsStream(csvFile)))) {

            while ((line = br.readLine()) != null) {
                if (header == null) {
                    header = line;
                } else {
                    // use comma as separator Header: id,first_name,last_name,email,event,phone,study_year,education_status,education_id
                    String[] elements = line.split(cvsSplitBy);
                    String firstName = elements[1];
                    String lastName = elements[2];
                    String phone = elements[5];
                    String email = elements[3];
                    Education education = this.educationRepository.findOne(Long.parseLong(elements[8]));

                    String educationStatus = elements[7];
                    int originalStudyYear = Integer.parseInt(elements[6]);
                    String event = elements[4];
                    Date dateOfAdding = null;


                    String dateToTransform = elements[9];
                    if (!dateToTransform.equals("0000-00-00") || !dateToTransform.isEmpty()) {
                        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                        d.setTimeZone(TimeZone.getTimeZone("UTC"));
                        dateOfAdding = d.parse(dateToTransform);
                    }

                    Candidate candidate =
                        new Candidate(firstName, lastName, phone, email, educationStatus, originalStudyYear, event, dateOfAdding);
                    candidate.setEducation(education);

                    this.candidateRepository.save(candidate);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void defaultMockData() {
        emptyDatabase();
        this.educationRepository.save(new Education("High-School", "Marie Curie", "Informatics", 3));
        this.educationRepository.save(new Education("Bachelor", "UBB", "Mathematics-Informatics", 4));
        Education education = new Education("Master", "UTCN", "Information Technology", 2);

        this.educationRepository.save(education);

        this.candidateRepository.save(new Candidate("Miralem", "Pjanic", "012986212", "aaa@a.a"));
        this.candidateRepository.save(new Candidate("Sami", "Khedira"));
        this.candidateRepository.save(new Candidate("Mario", "Mandzukic"));
        this.candidateRepository.save(new Candidate("Paulo", "Dybala"));
        Candidate test = new Candidate("Alex", "Sandro");
        test.setEducation(education);

        this.candidateRepository.save(test);

        this.tagRepository.save(new Tag("German", "foreign"));
        this.tagRepository.save(new Tag("English", "foreign"));
        Tag trial = new Tag("Java", "programming");
        this.tagRepository.save(trial);
        this.candidateRepository.save(test);

        this.candidateSkillsRepository.save(new CandidateSkills(test, trial, "average"));

        this.candidateNotesRepository.save(new CandidateNotes(test, "NEW", "Registered Java Conference", null));

    }

    private boolean isDatabaseEmpty() {
        return !this.candidateRepository.findAll().iterator().hasNext();
    }

}