package com.lin.nkcommunity;

import com.lin.nkcommunity.dao.DiscussPostMapper;
import com.lin.nkcommunity.dao.UserMapper;
import com.lin.nkcommunity.entity.DiscussPost;
import com.lin.nkcommunity.entity.User;
import com.lin.nkcommunity.service.DiscussPostService;
import com.lin.nkcommunity.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author Lin
 * @Date 2022/7/23 - 10:28
 */

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    DiscussPostService discussPostService;
    @Autowired
    UserService userService;
    @Test
    public void testUserSelect(){
        User user = userMapper.selectById(1);
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }
    @Test
    public void testCon(){
            List<DiscussPost> list = discussPostService.findDiscussPosts(0,0,10,0);
            List<Map<String,Object>> discussPosts = new ArrayList<>();
            if(list!=null){
                for(DiscussPost post : list)
                {
                    Map<String,Object> map = new HashMap<>();
                    map.put("post",post);
                    User user = userService.findUserById(post.getUserId());
                    map.put("user",user);
                    discussPosts.add(map);
                }
            }
            for(Map map: discussPosts){
                System.out.println(map);
            }
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123");
        user.setEmail("test@qq.com");
        user.setSalt("qwe");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser() {
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "test123");
        System.out.println(rows);
    }
    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 5,0);
        for (DiscussPost post : list) {
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

}
