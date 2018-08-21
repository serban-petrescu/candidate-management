package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.Education;
import ro.msg.cm.model.Tag;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.EducationRepository;
import ro.msg.cm.repository.TagRepository;
import ro.msg.cm.types.CandidateCheck;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        this.tagRepository = tagRepository;
    }

    @PostMapping("/list")
    public void downloadCandidateCsv(HttpServletResponse response) throws IOException {
        String[] headers = {"firstName", "lastName", "phone", "email", "educationStatus", "originalStudyYear", "event"};
        writeResponse(response, candidateRepository, Candidate.class);
        //Local : createCSV(new FileWriter(csvFileName),candidateRepository,Candidate.class);
    }


    @PostMapping("/education")
    public void downloadEducationCsv(HttpServletResponse response) throws IOException {
        writeResponse(response, educationRepository, Education.class);

    }

    @PostMapping("/tag")
    public void downloadTagCsv(HttpServletResponse response) throws IOException {
        writeResponse(response, tagRepository, Tag.class);
    }


    private void writeResponse(HttpServletResponse response, CrudRepository repository, Class obj) throws IOException {
        String csvFileName = obj.getSimpleName() + ".csv";
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);

        response.setContentType("text/csv");
        response.setHeader(headerKey, headerValue);

        createCSV(response.getWriter(), repository, obj);
    }

    private void createCSV(Writer writer, CrudRepository repo, Class obj) throws IOException {
        String[] headers = determineHeader(obj);
        Iterable repoList;
        // This is in place to read only the valid candidates
        if (repo instanceof CandidateRepository) {
            repoList = ((CandidateRepository) repo).findAllByCheckCandidate(CandidateCheck.VALIDATED);
        } else {
            repoList = repo.findAll();
        }
        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(headers);
        for (Object object : repoList) {
            csvWriter.write(object, headers);
        }
        csvWriter.close();
    }

    private String[] determineHeader(Class obj) {
        Field[] fields = obj.getDeclaredFields();
        List<String> header = new ArrayList<>();

        for (Field field : fields)
            if (!field.getName().equals("id")) {
                header.add(field.getName());
            }
        System.out.println(Collections.singletonList(header));
        String[] headers = new String[header.size()];
        return header.toArray(headers);
    }
}
