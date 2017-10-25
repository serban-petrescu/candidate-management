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

import ro.msg.cm.configuration.PropertiesLoader;
import ro.msg.cm.model.*;
import ro.msg.cm.repository.*;

import java.io.*;
import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CandidateRepository candidateRepository;

    private final TagRepository tagRepository;

    private final EducationRepository educationRepository;

    private final CandidateSkillsRepository candidateSkillsRepository;

    private final CandidateNotesRepository candidateNotesRepository;

    public static final String IMPORT_MOCK = "importMock";

    public static final String MOCK_CSV_FILE = "mockCSV";

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
        mock();
    }

    private void mock() {
        Properties properties= PropertiesLoader.loadPropertiesFile("./global.properties");
        String mock = properties.getProperty(IMPORT_MOCK);
        if(Boolean.valueOf(mock)){
            emptyDatabase();
            try {
                String csvFile = properties.getProperty(MOCK_CSV_FILE);
                importCSV(new FileInputStream(csvFile),candidateRepository,Candidate.class);
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

    /**
     * Imports the CSV into the repository
     * @param csvContent the content of the csv to be imported
     * @param rep the corresponding repository for storing the new data
     * @param tClass class for the to-be-imported records*/
    public static <T> void importCSV(InputStream csvContent, CrudRepository rep, Class<T> tClass) {
        try {
            BeanListProcessor<T> rowProcessor = new BeanListProcessor<T>(tClass);
            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.setRowProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(parserSettings);
            //this submits all rows parsed from the input to the BeanListProcessor
            parser.parse(csvContent);
            List<T> beans = rowProcessor.getBeans();
            rep.save(beans);
        } catch (Exception e) {
            log.error("Error at saving the entries");
        }
    }
}