package com.encurtador.repositoires;

import com.encurtador.Entity.Url;
import com.encurtador.repositorys.UrlRepository;
import org.assertj.core.api.OptionalIntAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class UrlRepositoryTest {
    @Autowired
    UrlRepository urlRepository;
    Url urlValid = new Url("matheus.com.br","ASDJAOK", LocalDateTime.now(),LocalDateTime.now().plusDays(2));
@BeforeEach
void setup () {
    urlRepository.save(urlValid);
}
    @Test
    public void findByUrlOriginalShouldReturnOptinialWhenUrlOriginalIsValid () {
        Optional<Url> result = urlRepository.findByUrlOriginal("matheus.com.br");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("matheus.com.br", result.get().getUrlOriginal());
    }
    @Test
    public void findByUrlOriginalShouldReturnEmptyOptinialWhenUrlOriginalIsNotValid () {
        Assertions.assertTrue(urlRepository.findByUrlOriginal("aa.com.br").isEmpty());
    }


    @Test
    public void findByShortUrlShouldReturnOptinialWhenUrlOriginalIsValid () {
        Assertions.assertTrue(urlRepository.findByShortUrl("ASDJAOK").isPresent());
    }
    @Test
    public void findByShortUrlShouldReturnEmptyOptinialWhenShortUrlIsNotValid () {
        Assertions.assertTrue(urlRepository.findByShortUrl("aa.com.br").isEmpty());
    }






}
