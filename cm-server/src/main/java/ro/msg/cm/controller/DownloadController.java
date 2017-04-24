package ro.msg.cm.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

	private final CandidateRepository repo;

	@Autowired
	public DownloadController(CandidateRepository repo) {
		this.repo = repo;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void downloadCsv(@RequestBody List<Candidate> candidates, HttpServletResponse response) throws IOException {

		String csvFileName = "candidates.csv";

		response.setContentType("text/csv");

		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);


		// uses the Super CSV API to generate CSV data from the model data
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "firstName", "lastName" };

		csvWriter.writeHeader(header);

		for (Candidate candidate : candidates) {
			csvWriter.write(candidate, header);
		}

		csvWriter.close();

	}
}
