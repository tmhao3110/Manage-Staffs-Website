/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.dao;

import java.io.Serializable;
import java.util.List;
import hao.model.User;
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
public class UserDAO {

    @Autowired
    protected JdbcTemplate jdbc;

    public void insertUser(User a) {
        String sql = "INSERT INTO USER VALUES (?, ?, ?, ?)";
        jdbc.update(sql, a.getUsername(), a.getPassword(), a.getFullname(), a.getImg());
    }
    
    public void updateUser(User a) {
        String sql = "UPDATE USER SET PASSWORD = ?, FULLNAME = ?, IMG = ? WHERE USERNAME = ?";
        jdbc.update(sql, a.getPassword(), a.getFullname(), a.getImg(),  a.getUsername());
    }
    
    public void deleteUser(String username) {
        String sql = "DELETE FROM USER WHERE USERNAME = ?";
        jdbc.update(sql, username);
    }

    public User getByUsername(Serializable Username) {
        String sql = "SELECT * FROM USER WHERE USERNAME = ?";
        return jdbc.queryForObject(sql, getRowMapper(), Username);
    }

    public List<User> getUser() {
        String sql = "SELECT * FROM USER";
        return getBySql(sql);
    }

    public List<User> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    private RowMapper<User> getRowMapper() {
        return new BeanPropertyRowMapper<>(User.class);
    }
}
