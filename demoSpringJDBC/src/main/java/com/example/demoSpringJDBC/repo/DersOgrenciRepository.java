package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.Ders_Ogrenci;
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
public class DersOgrenciRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Ders_Ogrenci> getall() {
        List<Ders_Ogrenci> dersOgrenci = new ArrayList<>();
        String sql = "SELECT \"KAYIT_ID\", \"OGRENCI_ID\", \"DERS_ID\"\n" +
                "\tFROM \"OBS\".\"DERS_OGRENCI\";";
        RowMapper<Ders_Ogrenci> rowMapper = new RowMapper<Ders_Ogrenci>() {
            @Override
            public Ders_Ogrenci mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Ders_Ogrenci(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
            }
        };
        dersOgrenci = jdbcTemplate.query(sql, rowMapper);
        return dersOgrenci;
    }

    public Ders_Ogrenci getById(int id) {
        String sql = "SELECT * FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"KAYIT_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Ders_Ogrenci>() {
            @Override
            public Ders_Ogrenci mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Ders_Ogrenci(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
            }
        }, id);
    }

    public boolean insert(Ders_Ogrenci dersOgrenci) {
        String sql = "INSERT INTO \"OBS\".\"DERS_OGRENCI\"(\"OGRENCI_ID\", \"DERS_ID\") VALUES (?,?)";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, dersOgrenci.getOgrenciId());
                ps.setInt(2, dersOgrenci.getDersId());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean update(Ders_Ogrenci dersOgrenci) {
        String sql = "UPDATE \"OBS\".\"DERS_OGRENCI\" SET \"OGRENCI_ID\"=?, \"DERS_ID\"=? WHERE \"KAYIT_ID\" = ?";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, dersOgrenci.getOgrenciId());
                ps.setInt(2, dersOgrenci.getDersId());
                ps.setInt(3, dersOgrenci.getKayitId());
                return ps.executeUpdate();
            }
        }) != null;
    }

    public boolean deleteById(int id) {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"KAYIT_ID\" = :KAYIT_ID";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("KAYIT_ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }

}
