package com.dmtd.hanfu.forum.service.impl;

import com.dmtd.hanfu.forum.dao.ArticleDao;
import com.dmtd.hanfu.forum.dao.CollectionDao;
import com.dmtd.hanfu.forum.dao.CommentDao;
import com.dmtd.hanfu.forum.dao.ThumbsUpDao;
import com.dmtd.hanfu.forum.dto.ArticleDto;
import com.dmtd.hanfu.forum.entity.Article;
import com.dmtd.hanfu.forum.entity.PageBean;
import com.dmtd.hanfu.forum.entity.TypeText;
import com.dmtd.hanfu.forum.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CollectionDao collectionDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ThumbsUpDao thumbsUpDao;

    @Override
    public List<Article> getArticleList() {
        return articleDao.getArticleList();
    }

    @Override
    public Article getArticleById(Integer articleId) {
        return articleDao.getArticleById(articleId);
    }

    @Override
    public int addArticle(String title, String content, Timestamp timestamp, Integer uid, String lable, Integer type) {
        return articleDao.addArticle(title, content, timestamp, uid, lable, type);
    }

    @Override
    public List<Article> getArticleByUID(Integer uid) {
        return articleDao.getArticleByUID(uid);
    }

    @Override
    public int deleteArticleByID(Integer articleId) {
        return articleDao.deleteArticleByID(articleId);
    }

    @Override
    public PageBean getArticlePageList(int currentPage, int pageSize, Integer type, Integer userId) {
        int count = articleDao.getArticleCount();
        int totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        List<Article> articleList = articleDao.getArticlePageList((currentPage - 1) * pageSize, pageSize, type, userId);
        List<ArticleDto> newArticleList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleDto newArticle = new ArticleDto();
            BeanUtils.copyProperties(article, newArticle);
            if (!StringUtils.isEmpty(article.getContent())) {
                String tempContent = null;
                if (article.getContent().contains("<img")) {
                    tempContent = article.getContent().replaceAll("<img.*>.*</img>", "")
                            .replaceAll("<img.*/>", "").replaceAll("<img.*>", "");
                } else {
                    if (article.getContent().replaceAll(" ", "").length() > 100) {
                        newArticle.setContent(article.getContent().replaceAll(" ", "").substring(0, 100));
                        newArticle.setIsBigContent(1);
                    } else {
                        newArticle.setContent(article.getContent());
                    }
                }
                if (!StringUtils.isEmpty(tempContent))
                    if (tempContent.replaceAll(" ", "").length() > 100) {
                        newArticle.setContent(tempContent.replaceAll(" ", "").substring(0, 100));
                        newArticle.setIsBigContent(1);
                    } else {
                        newArticle.setContent(tempContent);
                    }
                if (article.getContent().contains("<img")) {
                    String imgData = article.getContent().split("src=\"")[1].split("\"")[0];
                    newArticle.setImg(imgData);
                    newArticleList.add(newArticle);
                    continue;
                }
            }
            newArticleList.add(newArticle);
        }
        PageBean pageBean = new PageBean(currentPage, pageSize, count, totalPage, newArticleList);
        return pageBean;
    }

    @Override
    public List<Article> searchArticleByKey(String key) {
        return articleDao.searchArticleByKey(key);
    }

    @Override
    public List<Article> getArticleListByStatus(List<Integer> statusList) {
        return articleDao.getArticleListByStatus(statusList);
    }

    @Override
    public List<TypeText> getArticleActions(Integer articleId) {
        TypeText collectioinTypeText = collectionDao.getTypeTextByArticleId(articleId);
        TypeText commentTypeText = commentDao.getTypeTextByArticleId(articleId);
        TypeText thumbsUpTypeText = thumbsUpDao.getTypeTextByArticleId(articleId);
        List<TypeText> typeTexts = new ArrayList<>();
        if (collectioinTypeText != null) {
            typeTexts.add(collectioinTypeText);
        } else {
            collectioinTypeText = new TypeText();
            collectioinTypeText.setType("star-o");
            collectioinTypeText.setText("");
            typeTexts.add(collectioinTypeText);
        }
        if (commentTypeText != null) {
            typeTexts.add(commentTypeText);
        } else {
            commentTypeText = new TypeText();
            commentTypeText.setType("message");
            commentTypeText.setText("");
            typeTexts.add(commentTypeText);
        }
        if (thumbsUpTypeText != null) {
            typeTexts.add(thumbsUpTypeText);
        } else {
            thumbsUpTypeText = new TypeText();
            thumbsUpTypeText.setText("");
            thumbsUpTypeText.setType("like-o");
            typeTexts.add(thumbsUpTypeText);
        }

        return typeTexts;
    }

    @Override
    public List<Article> iCollectArticles(int currentPage, int pageSize, Integer type, Integer userId) {
        return articleDao.iCollectArticles(userId);
    }

}
