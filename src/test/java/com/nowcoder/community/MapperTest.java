package com.nowcoder.community;


import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user=userMapper.selectByName("liubei");
        System.out.println(user);

        user=userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void insertUser(){
        User user=new User();
        userMapper.insertUser(user);
    }

    @Test void updateUser(){
        userMapper.updateStatus(150,1);
        userMapper.updatePassword(150,"hello");
    }

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        System.out.println(list);
        int count=discussPostMapper.selectDiscussPostRows(0);
        System.out.println(count);
    }

    @Autowired
    MailClient mailClient;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void testMail(){
        mailClient.sendMail("1599267511@qq.com","TEST","WELCOME");
    }
    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","用户");

        String content=templateEngine.process("/mail/demo",context);
        System.out.println(content);

        mailClient.sendMail("1599267511@qq.com","HTML",content);
    }

    @Test
    public void hahaha(){
    }
}
