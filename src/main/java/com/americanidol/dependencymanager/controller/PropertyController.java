package com.americanidol.dependencymanager.controller;

import com.americanidol.dependencymanager.model.Dependency;
import com.americanidol.dependencymanager.model.Property;
import com.americanidol.dependencymanager.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    String app_runtime="app.runtime";
    String version="4.3.0";

    @RequestMapping(method = RequestMethod.POST,value="/projects/{projectName}/property")
    public  void addProperty( @PathVariable String projectName) {
        propertyService.addProperty(projectName);
    }
    @RequestMapping(method = RequestMethod.PUT,value="/projects/{projectName}/property")
    public  void updateProperty( @PathVariable String projectName) {
        propertyService.updateProperty(projectName);
//          System.out.println(projectName);
    }
}
