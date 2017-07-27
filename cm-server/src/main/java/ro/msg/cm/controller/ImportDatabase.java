package ro.msg.cm.controller;

/**
 * Created by oana on 5/16/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.Education;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.CandidateSkillsRepository;
import ro.msg.cm.repository.EducationRepository;
import ro.msg.cm.repository.TagRepository;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/import")
public class ImportDatabase {

    private final CandidateRepository candidateRepository;
    private final TagRepository tagRepository;
    private final EducationRepository educationRepository;
    private final CandidateSkillsRepository candidateSkillsRepository;

    @Autowired
    public ImportDatabase(CandidateRepository candidateRepository, TagRepository tagRepository, EducationRepository educationRepository, CandidateSkillsRepository candidateSkillsRepository) {

        this.candidateRepository = candidateRepository;
        this.tagRepository = tagRepository;
        this.educationRepository = educationRepository;
        this.candidateSkillsRepository = candidateSkillsRepository;
    }

    @RequestMapping(value = "/education", method = RequestMethod.POST)
    public void importEducation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.print("YUHU");
        Field[] fs = Education.class.getDeclaredFields();
        List<String> fields = new ArrayList<String>();
        for (Field field : fs) {
            fields.add(field.getName().toString());
        }
        System.out.print("response content");
        System.out.print(request.getInputStream());
        importCSV(educationRepository,Education.class.getSimpleName(),request.getInputStream(), fields);

    }


    private void importCSV(Repository rep, String tableName, InputStream csvContent, List<String> headers) {

        String line = "";
        String cvsSplitBy = ",";
        String header=null;

        BufferedReader in = new BufferedReader(new InputStreamReader(csvContent));
        try{
        StringBuilder responseData = new StringBuilder();
        while((line = in.readLine()) != null) {
            responseData.append(line);
        }
        System.out.print("!"+responseData);
        }
        catch (IOException e ){System.out.print("Something went wrong");}

        /* try (BufferedReader br = new BufferedReader(csvContent)) {

            while ((line = csvContent.read()) != 1) {
                if (header==null)
                    header = line;
                else
                {
                    // use comma as separator Header: id,first_name,last_name,email,event,phone,study_year,education_status,education_id
                    Education education = new Education();
                    String[] elements = line.split(cvsSplitBy);
                    Method[] mts = Education.class.getMethods();

                    this.educationRepository.save(education);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    private void loadFromMockDataCSV() {

        String csvFile = "src/main/resources/MockDataCSV.csv";
        String line = "";
        String cvsSplitBy = ",";
        String header=null;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                if (header==null)
                    header = line;
                else
                {
                    // use comma as separator Header: id,first_name,last_name,email,event,phone,study_year,education_status,education_id
                    String[] elements = line.split(cvsSplitBy);
                    String firstName=elements[1];
                    String lastName=elements[2];
                    String phone=elements[5];
                    String email=elements[3];
                    Education education=this.educationRepository.findOne(Long.parseLong(elements[8]));

                    String educationStatus=elements[7];
                    int studyYear=Integer.parseInt(elements[6]);
                    String event=elements[4];
                    Candidate candidate = new Candidate(firstName,lastName,phone, email,educationStatus,studyYear, event);
                    candidate.setEducation(education);

                    this.candidateRepository.save(candidate);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
