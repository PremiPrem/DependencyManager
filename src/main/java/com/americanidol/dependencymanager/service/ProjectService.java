package com.americanidol.dependencymanager.service;

import com.americanidol.dependencymanager.model.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    private List<Project> projects=new ArrayList<>(Arrays.asList(
            new Project("first-service-name","https://github.com/repo/service/repo","master"),
            new Project("second-service-name","https://github.com/repo/service2/repo","master")
    ));

      public List<Project> getAllProject(){
        return projects;
    }


}
