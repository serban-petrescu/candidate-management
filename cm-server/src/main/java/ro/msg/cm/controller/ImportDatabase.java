package ro.msg.cm.controller;

/**
 * Created by oana on 5/16/17.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class ImportDatabase {

    private final TagRepository tagRepository;
    private final EducationRepository educationRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public ImportDatabase(TagRepository tagRepository, EducationRepository educationRepository, CandidateRepository candidateRepository) {

        this.tagRepository = tagRepository;
        this.educationRepository = educationRepository;
        this.candidateRepository = candidateRepository;
    }

    @RequestMapping(value = "/education", method = RequestMethod.POST)
    public void importEducation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logRequestDetail(request);
        DatabaseLoader.importCSV(request.getInputStream(), educationRepository,Education.class);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public void importTag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logRequestDetail(request);
        DatabaseLoader.importCSV(request.getInputStream(), tagRepository, Tag.class);
    }

    @RequestMapping(value = "/candidate", method = RequestMethod.POST)
    public void importCandidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logRequestDetail(request);
        DatabaseLoader.importCSV(request.getInputStream(), candidateRepository, Candidate.class);
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
        String result = new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines().collect(Collectors.joining("\n"));
        log.debug("Result length is" + result.length());
        log.debug(result);

    }

}
