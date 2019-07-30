package com.wzt.uml.controller;

import com.wzt.uml.model.Article;
import com.wzt.uml.model.User;
import com.wzt.uml.service.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class RecordController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/record")
    @RequiresPermissions("write")
    public Object record() {
        return "record";
    }

    @RequiresPermissions("write")
    @PostMapping("/submit-record")
    public String submitRecord(Article article, Model model) {
        if (article.getContent().trim().isEmpty() || article.getTitle().trim().isEmpty()) {
            //如果标题或内容输入为空

            return "recode";

        } else {
            article.setTime(new Date());
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            String author = user.getUsername();
            article.setAuthor(author);
            int res = articleService.addArticle(article);
            if (res <= 0) {
                //添加到数据库失败

            }
        }
        return "redirect:/";
    }

    @GetMapping("/article-info")
    public String getArtcleInfo(int id,Model model){
        System.out.println("id = "+ id);
        Article article = articleService.getArticleByID(id);
        if (article == null){
            return "redirect:/";
        }
        model.addAttribute("title",article.getTitle());
        model.addAttribute("author",article.getAuthor());
        model.addAttribute("time",article.getTime());
        model.addAttribute("content",article.getContent());
        return "article";
    }

    @RequestMapping("/")
    public String submitRecord(Model model) {
        List<Article> articleList = articleService.getLatstArticle();
        Subject subject =SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        model.addAttribute("isShow","hidden");
        if (user != null){
            model.addAttribute("username","Hello! "+user.getUsername());
            model.addAttribute("isHidden","hidden");
            model.addAttribute("isShow",null);
            model.addAttribute("user_center","用户中心");
            if (!user.getRole().equals("normal")){
                model.addAttribute("perUser","全部成员");
            }
        }
        model.addAttribute("articleList",articleList);
        return "index";
    }

    @RequestMapping("/my-article")
    public String getPersonAt(Model model){
        Subject subject =SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Article> articles = articleService.getPersonalAT(user.getUsername());
        if (articles.size()!=0){
            model.addAttribute("articleList",articles);
        }
        return "myarticle";
    }
}
