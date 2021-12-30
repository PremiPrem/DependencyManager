package com.americanidol.dependencymanager.model;


public class Project {
   private String projectName;
   private String repoUrl;
   private String activeBranch;

   public Project(){

   }

    public Project(String projectName, String repoUrl, String activeBranch) {
        this.projectName = projectName;
        this.repoUrl = repoUrl;
        this.activeBranch = activeBranch;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getActiveBranch() {
        return activeBranch;
    }

    public void setActiveBranch(String activeBranch) {
        this.activeBranch = activeBranch;
    }
}
