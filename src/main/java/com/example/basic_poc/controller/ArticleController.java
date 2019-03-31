package com.example.basic_poc.controller;

import com.example.basic_poc.Repository.ArticleRepository;
import com.example.basic_poc.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

  @Autowired
  private ArticleRepository articleRepository;

  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  @RequestMapping(value = "/user/article", method = RequestMethod.GET)
  public List<Article> getAllArticle() {
    List<Article> articlelist;
    articlelist = this.articleRepository.findAll();
    return articlelist;
  }

  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  @RequestMapping(value = "/user/article", method = RequestMethod.POST)
  public void createArticle(@RequestBody Article article) {
    this.articleRepository.save(article);
  }
}
