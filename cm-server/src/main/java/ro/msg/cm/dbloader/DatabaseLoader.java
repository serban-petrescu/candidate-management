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

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ro.msg.cm.model.*;
import ro.msg.cm.repository.*;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CandidateRepository candidateRepository;

    private final TagRepository tagRepository;

    private final EducationRepository educationRepository;

    private final CandidateSkillsRepository candidateSkillsRepository;

    private final CandidateNotesRepository candidateNotesRepository;

    private final MockProperties mockProperties;

    @Autowired
    public DatabaseLoader(CandidateRepository candidateRepository, TagRepository tagRepository, EducationRepository educationRepository,
                          CandidateSkillsRepository candidateSkillsRepository, CandidateNotesRepository candidateNotesRepository, MockProperties mockProperties) {
        this.candidateRepository = candidateRepository;
        this.tagRepository = tagRepository;
        this.educationRepository = educationRepository;
        this.candidateSkillsRepository = candidateSkillsRepository;
        this.candidateNotesRepository = candidateNotesRepository;
        this.mockProperties = mockProperties;
    }

    @Override
    public void run(String... strings) throws Exception {
        mock();
    }

    private void mock() {
        if(mockProperties.getEnabled()){
            emptyDatabase();
            try {
                importCandidate(new FileInputStream(mockProperties.getLocation()));
            } catch (FileNotFoundException e) {
                log.error("Csv file not found");
            }
        }
    }
    private void emptyDatabase() {
        this.candidateNotesRepository.deleteAll();
        this.candidateSkillsRepository.deleteAll();
        this.candidateRepository.deleteAll();
        this.educationRepository.deleteAll();
        this.tagRepository.deleteAll();
    }


    private static <T> List processBeans(InputStream csvContent, Class<T> tClass) {

        BeanListProcessor<T> rowProcessor = new BeanListProcessor<>(tClass);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setRowProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(parserSettings);
        //this submits all rows parsed from the input to the BeanListProcessor
        parser.parse(csvContent);
        List<T> beans = rowProcessor.getBeans();
        return beans;

    }

    /**
     * Imports the CSV into the repository
     * @param csvContent the content of the csv to be imported
     * @param rep the corresponding repository for storing the new data
     * @param tClass class for the to-be-imported records*/
    public static <T> void importCSV(InputStream csvContent, CrudRepository rep, Class<T> tClass) {
        List beans = processBeans(csvContent,tClass);
        rep.save(beans);
    }

    private void importCandidate(InputStream csvContent) {
        List<Candidate> beans = processBeans(csvContent,Candidate.class);
        Education education = new Education("Mock Education Type","Mock Provider","Mock Descriptions",4);
        educationRepository.save(education);
        Date date = Date.valueOf(LocalDate.now());
        for(Candidate bean:beans){
            bean.setEducation(education);
            bean.setDateOfAdding(date);
        }
        candidateRepository.save(beans);
    }
}