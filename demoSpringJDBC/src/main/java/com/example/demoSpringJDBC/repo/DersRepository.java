package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.Ders;
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
public class DersRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Ders> getall() {
        List<Ders> ders = new ArrayList<>();
        String sql = "SELECT \"DERS_ID\", \"OGRETMEN_ID\", \"KONU_ID\"\n" +
                "\tFROM \"OBS\".\"DERS\";";
        RowMapper<Ders> rowMapper = new RowMapper<Ders>() {
            @Override
            public Ders mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Ders(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
            }
        };
        ders = jdbcTemplate.query(sql, rowMapper);
        return ders;
    }

    public Ders getById(int id) {
        String sql = "SELECT * FROM \"OBS\".\"DERS\" WHERE \"DERS_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Ders>() {
            @Override
            public Ders mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Ders(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
            }
        }, id);
    }

    public boolean insert(Ders ders) {
        String sql = "INSERT INTO \"OBS\".\"DERS\"(\n" +
                "\t\"OGRETMEN_ID\", \"KONU_ID\")\n" +
                "\tVALUES ( ?, ?);";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, ders.getOgretmenId());
                ps.setInt(2, ders.getKonuId());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean update(Ders ders) {
        String sql = "UPDATE \"OBS\".\"DERS\"\n" +
                "\tSET  \"OGRETMEN_ID\"=?, \"KONU_ID\"=?\n" +
                "\tWHERE \"DERS_ID\"=?;";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, ders.getOgretmenId());
                ps.setInt(2, ders.getKonuId());
                ps.setInt(3, ders.getDersId());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean deleteById(int id) {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"DERS\"\n" +
                "\tWHERE \"DERS_ID\"=:DERS_ID";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("DERS_ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }

}
