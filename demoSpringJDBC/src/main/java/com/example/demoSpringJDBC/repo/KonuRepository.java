package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.Konu;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
@AllArgsConstructor
public class KonuRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Konu> getall() {
        List<Konu> konu = new ArrayList<>();
        String sql = "SELECT \"KONU_ID\", \"KONU\"\n" +
                "\tFROM \"OBS\".\"KONU\";";
        RowMapper<Konu> rowMapper = new RowMapper<Konu>() {
            @Override
            public Konu mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Konu(resultSet.getInt(1), resultSet.getString(2));
            }
        };
        konu = jdbcTemplate.query(sql, rowMapper);
        return konu;
    }

    public Konu getById(int id) {
        String sql = "SELECT * FROM \"OBS\".\"KONU\" WHERE \"KONU_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Konu>() {
            @Override
            public Konu mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Konu(resultSet.getInt(1), resultSet.getString(2));
            }
        }, id);
    }

    public boolean insert(Konu konu) {
        String sql = "INSERT INTO \"OBS\".\"KONU\" (\"KONU\") VALUES (?);";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, konu.getKonu());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean update(Konu konu) {
        String sql = "UPDATE \"OBS\".\"KONU\"\r\n" + "	SET  \"KONU\"=?\r\n" + "	WHERE \"KONU_ID\" = ?";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, konu.getKonu());
                ps.setInt(2, konu.getId());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean deleteById(int id) {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"KONU\" WHERE \"KONU_ID\" = :KONU_IDD";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("KONU_IDD", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }
}
