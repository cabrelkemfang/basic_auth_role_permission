package com.example.basic_poc.model;

import javax.persistence.*;

@Entity
public class Article {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name="article_id")
  private int articleId;

  @Column(name="title")
  private String title;

  @Column(name="category")
  private String category;

  public Article() {
  }

  public int getArticleId() {
    return articleId;
  }

  public void setArticleId(int articleId) {
    this.articleId = articleId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}