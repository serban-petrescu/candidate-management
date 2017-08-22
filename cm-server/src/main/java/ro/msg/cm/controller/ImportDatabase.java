package ro.msg.cm.controller;

/**
 * Created by oana on 5/16/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.cm.model.Education;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.CandidateSkillsRepository;
import ro.msg.cm.repository.EducationRepository;
import ro.msg.cm.repository.TagRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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


        printRequestDetail(false,request);
        //importCSV(educationRepository,Education.class.getSimpleName(),request.getInputStream(), fields);
        importCSV(Education.class, request.getInputStream(),this.educationRepository);
    }

    private void printRequestDetail(boolean debug, HttpServletRequest request)throws IOException{
        if(!debug) return;
        System.out.print(request.getMethod());
        Enumeration params = request.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = (String)params.nextElement();
            System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
        }
        String result = new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines().collect(Collectors.joining("\n"));
        System.out.println(result.length());
        System.out.println(result);

    }

    private void importCSV(Class table, InputStream csvContent, CrudRepository rep) throws IOException{
        System.out.println("import education csv");
        String line = "";
        String cvsSplitBy = ", ";
        String headerCsvSplitBy=",";
        String[] headers = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(csvContent));
        try {
            while ((line = in.readLine()) != null) {
                if(headers==null){
                    headers=line.split(headerCsvSplitBy);
                    System.out.println(Arrays.toString(headers));
                    continue;
                }
                String[] elements = line.split(cvsSplitBy);
                System.out.println(Arrays.toString(elements));
                Object importObject = table.newInstance();
                Method[] mts = table.getDeclaredMethods();
                //System.out.println(Arrays.toString(mts));
                for (Method m : mts) {
                    for ( int index = 0;index<headers.length; index++) {
                        String headerElement = headers[index].substring(0,1).toUpperCase()+headers[index].substring(1);
                        System.out.println(m.getName()+headerElement);
                        if(m.getName().startsWith("set"+headerElement)){
                            try {
                                Class<?>[] parameterTypes = m.getParameterTypes();
                                if(parameterTypes[0].isPrimitive())
                                {
                                    m.invoke(importObject, Integer.parseInt(elements[index]));
                                }
                                else
                                m.invoke(importObject, elements[index]);
                                // Handle any exceptions thrown by method to be invoked.
                            } catch (Exception x) {
                               x.printStackTrace();
                            }
                        }
                    }
                }
                rep.save(importObject);
            }

        }
        catch (IOException e ){
            System.out.print("Something went wrong");} catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
