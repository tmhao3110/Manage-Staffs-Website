/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.dao;

import hao.model.Record;
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
public class RecordDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    public void insertRecord(Record a) {
        String sql = "INSERT INTO RECORD VALUES (default, ?, ?, ?, ?)";
        jdbc.update(sql, a.isRecordtype(), a.getRecorddate(), a.getRecordreason(), a.getStaffid());
    }

    public void updateRecord(Record a) {
        String sql = "UPDATE RECORD SET RECORDTYPE = ?, RECORDDATE = ?, RECORDREASON = ?, STAFFID = ? WHERE RECORDID = ?";
        jdbc.update(sql, a.isRecordtype(), a.getRecorddate(), a.getRecordreason(), a.getStaffid(), a.getRecordid());
    }

    public void deleteRecord(String RecordID) {
        String sql = "DELETE FROM RECORD WHERE RECORDID = ?";
        jdbc.update(sql, RecordID);
    }

    public Record getByRecordID(Serializable RecordID) {
        String sql = "SELECT * FROM RECORD WHERE RECORDID = ?";
        return jdbc.queryForObject(sql, getRowMapper(), RecordID);
    }

    public List<Record> getRecords() {
        String sql = "SELECT * FROM RECORD";
        return getBySql(sql);
    }

    public List<Record> gettt() {
        String sql = "SELECT staff.StaffName, count(record.RecordType)\n"
                + "FROM RECORD join staff on record.StaffID = staff.StaffID where recordtype =1\n"
                + "group by staff.StaffName";
        return getBySql(sql);
    }

    public List<Record> getkl() {
        String sql = "SELECT staff.StaffName, count(record.RecordType)\n"
                + "FROM RECORD join staff on record.StaffID = staff.StaffID where recordtype =0\n"
                + "group by staff.StaffName;";
        return getBySql(sql);
    }

    public List<Record> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    private RowMapper<Record> getRowMapper() {
        return new BeanPropertyRowMapper<>(Record.class);
    }
}
