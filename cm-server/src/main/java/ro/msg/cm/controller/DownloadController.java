package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

    private final CandidateRepository repo;

    @Autowired
    public DownloadController(CandidateRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void downloadCsv(HttpServletResponse response) throws IOException {

        String csvFileName = "candidates.csv";
        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        createCSV(response.getWriter());
        createCSV(new FileWriter(csvFileName));

    }


    private void createCSV(Writer writer) throws IOException {

        Iterable<Candidate> candidateList = repo.findAll();
        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

        String[] headerCandidate = {"firstName", "lastName", "phone", "email", "educationStatus", "originalStudyYear", "event"};
        csvWriter.writeHeader(headerCandidate);

        for (Candidate candidate : candidateList) {
            csvWriter.write(candidate, headerCandidate);
        }

        csvWriter.close();
    }
}
