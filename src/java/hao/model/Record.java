/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh Hao
 */
public class Record implements Serializable {

    int recordid;
    boolean recordtype;
    String recorddate;
    String recordreason;
    String staffid;
    String getThanhtich;
    String getNameThanhtich;
    String photo;
    String departid;
    String staffname;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    String date1;
    int tt;
    int kl;

    public Record() {
    }

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public boolean isRecordtype() {
        return recordtype;
    }

    public void setRecordtype(boolean recordtype) {
        this.recordtype = recordtype;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

    public String getRecordreason() {
        return recordreason;
    }

    public void setRecordreason(String recordreason) {
        this.recordreason = recordreason;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public int getGetThanhtich() {
        if (!this.recordtype) {
            return 0;
        } else {
            return 1;
        }
    }

    public String getGetNameThanhtich() {
        if (!this.recordtype) {
            return "Kỷ luật";
        } else {
            return "Thành tích";
        }
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public String getDate1() {
        try {
            return formatter.format(formatter2.parse(recorddate));
        } catch (ParseException ex) {
            return recorddate;
        }
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public int getTt() {
        return 1;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }

    public int getKl() {
        return 0;
    }

    public void setKl(int kl) {
        this.kl = kl;
    }
    
    
    
}
