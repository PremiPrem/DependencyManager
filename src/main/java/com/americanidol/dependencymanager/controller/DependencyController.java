package com.americanidol.dependencymanager.controller;

import com.americanidol.dependencymanager.model.Dependency;
import com.americanidol.dependencymanager.model.Project;
import com.americanidol.dependencymanager.service.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DependencyController {

    @Autowired
    private DependencyService dependencyService;

    @RequestMapping("/projects/dependency")
    public List<Dependency> dependency(){
        return dependencyService.getAllDependency();
    }

    @RequestMapping(method = RequestMethod.POST,value="/projects/{projectName}/dependency")
    public  void addDependency( @PathVariable String projectName) {
        dependencyService.addDependency(projectName);
    }
    @RequestMapping(method = RequestMethod.PUT,value="/projects/{projectName}/dependency")
    public  void updateDependency(@PathVariable String projectName) {
        dependencyService.updateDependency(projectName);
    }

}
