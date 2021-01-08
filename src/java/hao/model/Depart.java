/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.model;

import java.io.Serializable;

/**
 *
 * @author Minh Hao
 */
public class Depart implements Serializable {

    String departid;
    String departname;
    String depimg;
    boolean delstatus;
    String doiten;
    int getCombobox;

    public Depart() {
    }

    
    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDepimg() {
        return depimg;
    }

    public void setDepimg(String depimg) {
        this.depimg = depimg;
    }

    public boolean isDelstatus() {
        return delstatus;
    }

    public void setDelstatus(boolean delstatus) {
        this.delstatus = delstatus;
    }

    public String getDoiten() {
        if (!this.delstatus) {
            return "Ngưng hoạt động";
        } else {
            return "Đang hoạt động";
        }
    }

    public void setDoiten(String doiten) {
        this.doiten = doiten;
    }

    public int getGetCombobox() {
        if(!delstatus) {
            return 0;
        } else {
            return 1;
        }
    }

   
}
