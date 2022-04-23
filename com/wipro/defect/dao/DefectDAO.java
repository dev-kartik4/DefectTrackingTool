package com.wipro.defect.dao;

import com.wipro.defect.bean.DefectBean;
import com.wipro.defect.exception.InvalidDefectRaiseException;
import com.wipro.defect.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DefectDAO {

    DBUtil dbu = new DBUtil();
    static int default_seq = 1000;

    public boolean validateDefect(DefectBean dbean) {
        boolean result = false;
        try {
            if(dbean.getPriority().equalsIgnoreCase("Very High") ||
                    dbean.getPriority().equalsIgnoreCase("High") ||
                    dbean.getPriority().equalsIgnoreCase("Medium") ||
                    dbean.getPriority().equalsIgnoreCase("Low"))
                result = true;
            else
                throw new InvalidDefectRaiseException("Invalid Input");
        } catch (InvalidDefectRaiseException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int generateDefectID() {

        int defectId = default_seq;
        while(dbu.checkData(defectId)){
            defectId = defectId + 1;
        }
        //System.out.println("DEFECT ID FROM CHECK DATA METHOD IS "+defectId);
        return defectId;
    }

    public String addDefect(DefectBean dbean) {
        generateDefectID();
        if (validateDefect(dbean)) {
            dbean.setDefectID(generateDefectID());
            dbean.setStatus("Pending");
            dbu.insertData(dbean);
            return "VALIDATE INSERT SUCCESS";
        } else
            return "VALIDATION FAILED";
    }

    public String changeStatus(int defectID, String status) {

        if(dbu.updateDefect(defectID,status).equals("SUCCESS")){
            return "UPDATE SUCCESS";
        }else{
            return "UPDATE FAILURE";
        }
    }

    public List<DefectBean> getDefectsByPriority(String priority, DefectBean dbean){
        List<DefectBean> priorityList = new ArrayList<DefectBean>();
        if(Stream.of("Very High", "High", "Low", "Medium").anyMatch(s -> dbean.getPriority().equalsIgnoreCase(s))) {
            priorityList.add(dbean);
        }else{
            return null;
        }
        return priorityList;
    }
}
