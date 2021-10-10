package com.performance.domain.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.performance.domain.entity.UserInfo;

@Repository
public class UserInfoDao {

    private JdbcTemplate jdbcTemplate;
    
    public UserInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insert (UserInfo entity) {
        String sql = "INSERT INTO user_info (last_name, first_name, prefectures, city, blood_type, hobby1, hobby2, hobby3, hobby4, hobby5)";
        sql = sql + " VALUES (";
        sql = sql + "'" + entity.getLastName() + "', ";
        sql = sql + "'" + entity.getFirstName() + "', ";
        sql = sql + "'" + entity.getPrefectures() + "', ";
        sql = sql + "'" + entity.getCity() + "', ";
        sql = sql + "'" + entity.getBloodType() + "', ";
        sql = sql + "'" + entity.getHobby1() + "', ";
        sql = sql + "'" + entity.getHobby2() + "', ";
        sql = sql + "'" + entity.getHobby3() + "', ";
        sql = sql + "'" + entity.getHobby4() + "', ";
        sql = sql + "'" + entity.getHobby5() + "')";
        jdbcTemplate.execute(sql);
    }

    public List<UserInfo> search() {
        String sql = "SELECT id, last_name, first_name, prefectures, city, blood_type, hobby1, hobby2, hobby3, hobby4, hobby5 ";
        sql = sql + "FROM user_info ";
        sql = sql + "WHERE last_name || first_name <> " + "'試験太郎'";
        RowMapper<UserInfo> mapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);
        return jdbcTemplate.query(sql, mapper);
    }

    public List<UserInfo> search2(UserInfo entity) {
        String sql = "SELECT id, last_name, first_name, prefectures, city, blood_type, hobby1, hobby2, hobby3, hobby4, hobby5 ";
        sql = sql + "FROM user_info ";
        sql = sql + "WHERE blood_type = '" + entity.getBloodType() + "' AND city = '"+ entity.getCity() + "'";
        sql = sql + "AND (hobby1 = '" + entity.getHobby1() + "' OR hobby1 = '" + entity.getHobby2() + "' OR hobby1 = '" + entity.getHobby3() + "' OR hobby1 = '" + entity.getHobby4() + "' OR hobby1 = '" + entity.getHobby5() + "')";
        sql = sql + "AND (hobby2 = '" + entity.getHobby1() + "' OR hobby2 = '" + entity.getHobby2() + "' OR hobby2 = '" + entity.getHobby3() + "' OR hobby2 = '" + entity.getHobby4() + "' OR hobby2 = '" + entity.getHobby5() + "')";
        sql = sql + "AND (hobby3 = '" + entity.getHobby1() + "' OR hobby3 = '" + entity.getHobby2() + "' OR hobby3 = '" + entity.getHobby3() + "' OR hobby3 = '" + entity.getHobby4() + "' OR hobby3 = '" + entity.getHobby5() + "')";
        sql = sql + "AND (hobby4 = '" + entity.getHobby1() + "' OR hobby4 = '" + entity.getHobby2() + "' OR hobby4 = '" + entity.getHobby3() + "' OR hobby4 = '" + entity.getHobby4() + "' OR hobby4 = '" + entity.getHobby5() + "')";
        sql = sql + "AND (hobby5 = '" + entity.getHobby1() + "' OR hobby5 = '" + entity.getHobby2() + "' OR hobby5 = '" + entity.getHobby3() + "' OR hobby5 = '" + entity.getHobby4() + "' OR hobby5 = '" + entity.getHobby5() + "')";
        RowMapper<UserInfo> mapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);
        System.out.println(sql);
        return jdbcTemplate.query(sql, mapper);
    }
    
    public UserInfo getTargetUser() {
        String sql = "SELECT id, last_name, first_name, prefectures, city, blood_type, hobby1, hobby2, hobby3, hobby4, hobby5 ";
        sql = sql + "FROM user_info ";
        sql = sql + "WHERE last_name = " + "'試験'";
        sql = sql + "AND first_name = " + "'太郎'";
        RowMapper<UserInfo> mapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);
        return jdbcTemplate.queryForObject(sql, mapper);
    }
    
    public int searchCount() {
        String sql = "SELECT COUNT(*) FROM user_info";
        
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void truncate() {
        String sql = "TRUNCATE TABLE user_info";
        jdbcTemplate.execute(sql);
    }
    
}
