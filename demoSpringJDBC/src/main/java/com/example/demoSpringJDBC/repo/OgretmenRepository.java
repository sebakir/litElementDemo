package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.Ogretmen;
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
public class OgretmenRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Ogretmen> getAll() {
        List<Ogretmen> ogretmen = new ArrayList<>();
        String sql = "SELECT * FROM \"OBS\".\"OGRETMEN\"";
        RowMapper<Ogretmen> rowMapper = new RowMapper<Ogretmen>() {
            @Override
            public Ogretmen mapRow(ResultSet rs, int rowNum) throws SQLException {
                // strategy pattern
                return new Ogretmen(rs.getInt(1), rs.getString(2));
            }
        };
        ogretmen = jdbcTemplate.query(sql, rowMapper);
        return ogretmen;
    }

    public Ogretmen getById(int id) {
        // prepstat
        String sql = "SELECT * FROM \"OBS\".\"OGRETMEN\" WHERE \"OGR_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Ogretmen>() {
            @Override
            public Ogretmen mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Ogretmen(rs.getInt(1), rs.getString(2));
            }
        }, id);
    }

    public boolean insertJDBC(Ogretmen ogretmen) {
        // prepstat
        String sql = "INSERT INTO \"OBS\".\"OGRETMEN\"( \"OGR_NAME\") VALUES (?)";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, ogretmen.getName());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean update(Ogretmen ogretmen) {
        String sql = "UPDATE \"OBS\".\"OGRETMEN\"\r\n" + "	SET  \"OGR_NAME\"=?\r\n" + "	WHERE \"OGR_ID\" = ?";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, ogretmen.getName());
                ps.setInt(2, ogretmen.getId());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean deleteById(int id) {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"OGRETMEN\" WHERE \"OGR_ID\" = :OGRETMEN_ID";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("OGRETMEN_ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }
}
