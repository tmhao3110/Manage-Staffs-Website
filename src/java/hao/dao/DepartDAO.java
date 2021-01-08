/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.dao;

import hao.model.Depart;
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
public class DepartDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    public void insertDepart(Depart a) {
        String sql = "INSERT INTO DEPART VALUES (?, ?, ?, ?)";
        jdbc.update(sql, a.getDepartid(), a.getDepartname(), a.getDepimg(), a.isDelstatus());
    }

    public void updateDepart(Depart a) {
        String sql = "UPDATE DEPART SET DEPARTNAME = ?, DEPIMG = ?, DELSTATUS = ? WHERE DEPARTID = ?";
        jdbc.update(sql, a.getDepartname(), a.getDepimg(), a.isDelstatus(), a.getDepartid());
    }

    public void deleteDepart(String ID) {
        String sql = "DELETE FROM DEPART WHERE DEPARTID = ?";
        jdbc.update(sql, ID);
    }

    public Depart getByDepartID(Serializable DepartID) {
        String sql = "SELECT * FROM DEPART WHERE DEPARTID = ?";
        return jdbc.queryForObject(sql, getRowMapper(), DepartID);
    }

    public List<Depart> getDeparts() {
        String sql = "SELECT * FROM DEPART";
        return getBySql(sql);
    }

    public List<Depart> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    private RowMapper<Depart> getRowMapper() {
        return new BeanPropertyRowMapper<>(Depart.class);
    }
}
