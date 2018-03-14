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
public class DatabaseLoader {

    private static <T> List processBeans(InputStream csvContent, Class<T> tClass) {

        BeanListProcessor<T> rowProcessor = new BeanListProcessor<>(tClass);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setRowProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(parserSettings);
        //this submits all rows parsed from the input to the BeanListProcessor
        parser.parse(csvContent);
        return rowProcessor.getBeans();

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

}