package com.example.basic_poc.Repository;

import com.example.basic_poc.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
