package ro.msg.cm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.cm.dbloader.DatabaseLoader;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.Education;
import ro.msg.cm.model.Tag;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.EducationRepository;
import ro.msg.cm.repository.TagRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final TagRepository tagRepository;
    private final EducationRepository educationRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public ImportController(TagRepository tagRepository, EducationRepository educationRepository, CandidateRepository candidateRepository) {

        this.tagRepository = tagRepository;
        this.educationRepository = educationRepository;
        this.candidateRepository = candidateRepository;
    }

    @PostMapping("/education")
    public void importEducation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatabaseLoader.importCSV(request.getInputStream(), educationRepository,Education.class);
        logRequestDetail(request);
    }

    @PostMapping("/tag")
    public void importTag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatabaseLoader.importCSV(request.getInputStream(), tagRepository, Tag.class);
        logRequestDetail(request);
    }

    @PostMapping("/candidate")
    public void importCandidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatabaseLoader.importCSV(request.getInputStream(), candidateRepository, Candidate.class);
        logRequestDetail(request);
    }

    /**
     * Logs the details like header names, parameters names and content of the request
     * only on debug level
     * */
    private void logRequestDetail(HttpServletRequest request) throws IOException {
        log.debug(request.getMethod());
        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            log.debug("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
        }
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            log.debug("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
        }
        /*You can only read the InputStream once, when you actually import it.
        String result = new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines().collect(Collectors.joining("\n"));
        log.debug("Result length is" + result.length());
        log.debug(result);
        */
    }

}
