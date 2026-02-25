package com.encurtador.repositorys;

import com.encurtador.Entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url,Long> {
    Optional<Url> findByUrlOriginal(String UrlOriginal);
    Optional<Url> findByShortUrl(String UrlOriginal);
}
