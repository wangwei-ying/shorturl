package com.github.wwying.repository;

import com.github.wwying.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class ShortUrlRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ShortUrl> listByHashCodeAndUrlAndEnabled(ShortUrl url) {
        return jdbcTemplate.query("select ID id,short_token shortToken,url ,enabled ,hash_code  from short_url where hash_code = ? and url = ? and enabled = ?",
               new Object[]{url.getHashCode(),url.getUrl(),url.getEnabled()},new BeanPropertyRowMapper<ShortUrl>(ShortUrl.class));
    }

    public ShortUrl save(ShortUrl url) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps =     con.prepareStatement("insert into short_url (short_token,url,enabled,hash_code) values (?,?,?,?)");

                ps.setString(1,url.getShortToken());
                ps.setString(2,url.getUrl());
                ps.setString(3,url.getEnabled());
                ps.setInt(4,url.getHashCode());

                return ps;
            }
        },keyHolder);
        url.setId(keyHolder.getKey().longValue());
        return url;
    }

    public void updateShortToeknById(ShortUrl url) {
        jdbcTemplate.update("update short_url set short_token  = ? where id = ?",url.getShortToken(),url.getId());
    }

    public ShortUrl getByShortToekn(ShortUrl url) {
        List<ShortUrl> query = jdbcTemplate.query("select ID id,short_token shortToken,url ,enabled ,hash_code hashCode  from short_url where short_token = ?", new Object[]{url.getShortToken()}, new BeanPropertyRowMapper<ShortUrl>(ShortUrl.class));
        return  query.size()>0?query.get(0):null;
    }
}
