package com.wipro.defect.util;

import com.wipro.defect.bean.DefectBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUtil {

    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/myrogdb";

            con = DriverManager.getConnection(url, "root", "tiger");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        if (con != null) {
//            System.out.println("DATABASE CONNECTED SUCCESSFULLY");
//        } else {
//            System.out.println("DATABASE CONNECTED FAILED");
//        }
        return con;
    }

    public boolean checkData(int defectId) {

        try {
            getConnection();
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/myrogdb";

            Connection con = DriverManager.getConnection(url, "root", "tiger");

            PreparedStatement ps = con.prepareStatement("select * from defect_table where defectid=?");
            ps.setInt(1, defectId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                //System.out.println("LAST ROW DEFECT ID IS "+rs.getInt(1));
                //System.out.println("NO DATA PRESENT WITH THIS DEFECT ID " + defectId);
                return rs.isLast();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertData(DefectBean dBean) {
        Date dat = new Date();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/myrogdb";

            Connection con = DriverManager.getConnection(url, "root", "tiger");

            PreparedStatement ps = con.prepareStatement("insert into defect_table values(?,?,?,?,?,?,?)");
            ps.setInt(1, dBean.getDefectID());
            ps.setString(2, dBean.getIssue());
            ps.setString(3, new SimpleDateFormat("dd-MM-yy").format(dBean.getDateOfCreation()));
            ps.setString(4, dBean.getModule());
            ps.setString(5, dBean.getPriority());
            ps.setString(6, dBean.getAssignedTo());
            ps.setString(7, dBean.getStatus());

            PreparedStatement ps1 = con.prepareStatement("insert into defect_log values(?,?,?)");
            ps1.setInt(1, dBean.getDefectID());
            ps1.setString(2, new SimpleDateFormat("dd-MM-yy").format(dBean.getDateOfCreation()));
            ps1.setString(3, "Pending with " +dBean.getAssignedTo());

            int i = ps.executeUpdate();
            System.out.println(i + "RECORDS UPDATED IN DEFECT TABLE");

            int j = ps1.executeUpdate();
            System.out.println(j + "RECORDS UPDATED IN DEFECT LOG");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String updateDefect(int defectId,String status){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/myrogdb";

            Connection con = DriverManager.getConnection(url,"root","tiger");

            PreparedStatement ps = con.prepareStatement("update defect_table set status=? where defectid=?");
            ps.setString(1,status);
            ps.setInt(2,defectId);

            PreparedStatement ps1 = con.prepareStatement("update defect_log set status=? where defectid=?");
            ps1.setString(1,status);
            ps1.setInt(2,defectId);

            int i = ps.executeUpdate();
            System.out.println("DEFECT TABLE WITH STATUS UPDATED FOR DEFECT ID "+defectId);

            int j = ps1.executeUpdate();
            System.out.println("DEFECT LOG WITH STATUS UPDATED FOR DEFECT ID "+defectId);

            return "SUCCESS";

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILURE";
    }
}
