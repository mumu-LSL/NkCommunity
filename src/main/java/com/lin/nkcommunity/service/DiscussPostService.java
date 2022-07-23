package com.lin.nkcommunity.service;


import com.lin.nkcommunity.dao.DiscussPostMapper;
import com.lin.nkcommunity.entity.DiscussPost;
import com.lin.nkcommunity.entity.ReplyPostResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


@Service
public class DiscussPostService {
    @Autowired
    DiscussPostMapper discussPostMapper;
    private static final Logger logger = LoggerFactory.getLogger(DiscussPostService.class);
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit, int orderMode) {


        logger.debug("load post list from DB.");
        return discussPostMapper.selectDiscussPosts(userId, offset, limit, orderMode);
    }

    public List<ReplyPostResult> findReplyDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectReplyDiscussPosts(userId,offset,limit);
    }

    public int findDiscussPostRows(int userId) {

        logger.debug("load post rows from DB.");
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    // 添加一个帖子
    public int addDiscussPost(DiscussPost post) {
        if (post == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        // 转义HTML标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        return discussPostMapper.insertDiscussPost(post);

    }

    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }

    public int updateCommentCount(int id, int commentCount){
        return discussPostMapper.updateCommentCount(id,commentCount);
    }

    public int updateType(int id, int type) {
        return discussPostMapper.updateType(id, type);
    }

    public int updateStatus(int id, int status) {
        return discussPostMapper.updateStatus(id, status);
    }

    public int updateScore(int id, double score){
        return discussPostMapper.updateScore(id,score);
    }

}
