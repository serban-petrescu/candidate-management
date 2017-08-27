package ro.msg.cm.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.EducationRepository;
import ro.msg.cm.repository.TagRepository;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

    private final CandidateRepository candidateRepository;
    private final TagRepository tagRepository;
    private final EducationRepository educationRepository;

    @Autowired
    public DownloadController(CandidateRepository candidateRepository, EducationRepository educationRepository, TagRepository tagRepository) {
       this.candidateRepository = candidateRepository;
       this.educationRepository = educationRepository;
       this.tagRepository =tagRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void downloadCsv(HttpServletResponse response) throws IOException {

        String csvFileName = "candidates.csv";
        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);

        String[] headers= {"firstName", "lastName", "phone", "email", "educationStatus", "originalStudyYear", "event"};
        createCSV(response.getWriter(),candidateRepository,headers);
        createCSV(new FileWriter(csvFileName),candidateRepository,headers);

    }


    @RequestMapping(value = "/education", method = RequestMethod.POST)
    public void downloadEducationCsv(HttpServletResponse response) throws IOException {

        String csvFileName = "education.csv";
        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);

        String[] headers={"educationType","provider","description","duration"};
        createCSV(response.getWriter(), educationRepository,headers);
        createCSV(new FileWriter(csvFileName),educationRepository, headers);

    }
    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public void downloadTagCsv(HttpServletResponse response) throws IOException {

        String csvFileName = "tag.csv";
        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);

        String[] headers={"description","tagType"};
        createCSV(response.getWriter(),tagRepository,headers);
        createCSV(new FileWriter(csvFileName),tagRepository,headers);

    }

    private void createCSV(Writer writer, CrudRepository repo, String[] headers) throws IOException {

        Iterable repoList = repo.findAll();
        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(headers);

        for (Object object : repoList) {
            csvWriter.write(object, headers);
        }

        csvWriter.close();
    }
}
