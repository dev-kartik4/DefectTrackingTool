package com.wipro.defect.bean;

import java.util.Date;

public class DefectBean {

    private int defectID;

    private String issue;

    private String module;

    private String priority;

    private String assignedTo;

    private Date dateOfCreation;

    private String status;

    public DefectBean() {
    }

    public DefectBean(int defectID, String issue, String module, String priority, String assignedTo, Date dateOfCreation, String status) {
        this.defectID = defectID;
        this.issue = issue;
        this.module = module;
        this.priority = priority;
        this.assignedTo = assignedTo;
        this.dateOfCreation = dateOfCreation;
        this.status = status;
    }

    public int getDefectID() {
        return defectID;
    }

    public void setDefectID(int defectID) {
        this.defectID = defectID;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DefectBean{" +
                "defectID=" + defectID +
                ", issue='" + issue + '\'' +
                ", module='" + module + '\'' +
                ", priority='" + priority + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", status='" + status + '\'' +
                '}';
    }
}
