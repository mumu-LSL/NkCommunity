package com.lin.nkcommunity.controller;

import com.lin.nkcommunity.entity.DiscussPost;
import com.lin.nkcommunity.entity.Page;
import com.lin.nkcommunity.entity.User;
import com.lin.nkcommunity.service.DiscussPostService;
import com.lin.nkcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lin
 * @Date 2022/7/23 - 11:15
 */
@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        //查询10条数据
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit(), 0);
        //用Map把post和user装到一起
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        //遍历post，用post里的id查询user
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        //把我们准备好的数据传给model
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("page",page);
        //返回模板
        return "/index";
    }
}