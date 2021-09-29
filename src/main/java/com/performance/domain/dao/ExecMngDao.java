package com.performance.domain.dao;

import java.util.List;

import com.performance.domain.entity.ExecMng;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExecMngDao {

    private JdbcTemplate jdbcTemplate;
    
    public ExecMngDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insert (ExecMng entity) {
        String sql = "INSERT INTO exec_mng (start_time)";
        sql = sql + " VALUES (?)";
        jdbcTemplate.update(sql, entity.getStartTime());
    }

    @Transactional
    public void update (ExecMng entity) {
        String sql = "UPDATE exec_mng SET end_time = ?";
        jdbcTemplate.update(sql, entity.getEndTime());
    }

    public List<ExecMng> search() {
        String sql = "SELECT start_time, end_time FROM exec_mng";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ExecMng>(ExecMng.class));
    }

    public void truncate() {
        String sql = "TRUNCATE TABLE exec_mng";
        jdbcTemplate.execute(sql);
    }
    
}
