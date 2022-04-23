package com.wipro.defect.service;

import com.wipro.defect.bean.DefectBean;
import com.wipro.defect.dao.DefectDAO;

import java.util.Date;

public class MainClass {

    public static void main(String[] args) {

        DefectDAO d = new DefectDAO();

        DefectBean dbean = new DefectBean();
        dbean.setIssue("Login Unauthorized");
        dbean.setModule("Login");
        dbean.setPriority("Very High");
        dbean.setAssignedTo("Prasath");

        Date dat = new Date();
        System.out.println(dat);
        dbean.setDateOfCreation(dat);
        String addStatus = d.addDefect(dbean);
        System.out.println(addStatus);

        //System.out.println(d.getDefectsByPriority("VERY HIGH",dbean));
        //System.out.println(d.changeStatus(1001,"Success"));

    }
}
