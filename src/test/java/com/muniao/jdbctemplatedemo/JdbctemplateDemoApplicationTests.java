package com.muniao.jdbctemplatedemo;

import com.muniao.jdbctemplatedemo.domain.User;
import com.muniao.jdbctemplatedemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbctemplateDemoApplicationTests
{

    @Autowired
    UserService userService;

    @Test
    public void test1()
    {
        User user = new User();
        user.setName("李小鹏");
        user.setAge(30);
        userService.addUser(user);
    }

    @Test
    public void test2()
    {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        userService.addUser2(user);
    }

    @Test
    public void test3()
    {
        List<User> list = userService.getUserList();
        System.out.println(list);
    }

}
