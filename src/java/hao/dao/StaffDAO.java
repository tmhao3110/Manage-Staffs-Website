/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.dao;

import hao.model.Staff;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Minh Hao
 */
@Repository
public class StaffDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    public void insertStaff(Staff a) {
        String sql = "INSERT INTO STAFF VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, a.getStaffid(), a.getStaffname(), a.isGender(), a.getBirthday(), a.getPhoto(), a.getEmail(), a.getSalary(), a.getNotes(), a.getDepartid(), a.isDeleted());
    }

    public void updateStaff(Staff a) {
        String sql = "UPDATE STAFF SET STAFFNAME = ?, GENDER = ?, BIRTHDAY = ?, PHOTO = ?, EMAIL = ?, SALARY = ?, NOTES = ?, DEPARTID = ?, DELETED = ? WHERE STAFFID = ?";
        jdbc.update(sql, a.getStaffname(), a.isGender(), a.getBirthday(), a.getPhoto(), a.getEmail(), a.getSalary(), a.getNotes(), a.getDepartid(), a.isDeleted(), a.getStaffid());
    }

    public void deleteStaff(String StaffID) {
        String sql = "DELETE FROM STAFF WHERE STAFFID = ?";
        jdbc.update(sql, StaffID);
    }

    public Staff getByStaffID(Serializable StaffID) {
        String sql = "SELECT * FROM STAFF WHERE STAFFID = ?";
        return jdbc.queryForObject(sql, getRowMapper(), StaffID);
    }
    public List<Staff> gettop10(){
        String sql="select staff.StaffName, Photo, DepartID \n" +
"from record join staff on record.StaffID = staff.StaffID\n" +
"where RecordType = 1\n" +
"group by staff.StaffID\n" +
"order by count(*) desc\n" +
"limit 10";
        return getBySql(sql);
    }
    public List<Staff> getStaffs() {
        String sql = "SELECT * FROM STAFF";
        return getBySql(sql);
    }

    public List<Staff> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    private RowMapper<Staff> getRowMapper() {
        return new BeanPropertyRowMapper<>(Staff.class);
    }
}
