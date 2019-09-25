package com.muniao.jdbctemplatedemo.service;

import com.muniao.jdbctemplatedemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addUser(User user)
    {
        return jdbcTemplate.update("insert into user (name,age) values (?,?)", user.getName(), user.getAge());

    }

    public int addUser2(User user)
    {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        /*int update = jdbcTemplate.update(new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
            {
                PreparedStatement ps = connection.prepareStatement("insert into user (name,age) values (?,?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
                return ps;
            }
        }, keyHolder);*/

        int update = jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement("insert into user (name,age) values (?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            return ps;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        System.out.println(user);
        return update;
    }


    public int deleteUserById(Long id)
    {
        return jdbcTemplate.update("delete from user where id=?", id);
    }

    public int updateUserById(User user )
    {
        return jdbcTemplate.update("update user set name=?,age=? where id=?", user.getName(),user.getAge());
    }

    public List<User> getUserList()
    {
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }
}
