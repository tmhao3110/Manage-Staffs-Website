/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Minh Hao
 */
public class Staff implements Serializable {

    String staffid;
    String staffname;
    boolean gender;
    String birthday;
    String photo;
    String email;
    float salary;
    String notes;
    String departid;
    boolean deleted;
    int getGioitinh;
    String getTrangthai;
    String nameGioitinh;
    String nameTrangthai;
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    String date1;

    public Staff() {
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getGetGioitinh() {
        if (!gender) {
            return 0;
        } else {
            return 1;
        }
    }

    public int getGetTrangthai() {
        if (!deleted) {
            return 0;
        } else {
            return 1;
        }
    }

    public String getNameGioitinh() {
        if (!this.gender) {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    public String getNameTrangthai() {
        if (!this.deleted) {
            return "Đang làm";
        } else {
            return "Nghĩ việc";
        }
    }
    
        public String getDate1() {
        try {
            return formatter.format(formatter2.parse(birthday));
        } catch (ParseException ex) {
            return birthday;
        }
    }

}
