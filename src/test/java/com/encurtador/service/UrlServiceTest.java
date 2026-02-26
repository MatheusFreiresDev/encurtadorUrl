package com.encurtador.service;

import com.encurtador.Entity.Url;
import com.encurtador.dtos.UrlDto;
import com.encurtador.execeptions.UrlExpired;
import com.encurtador.execeptions.UrlNotFounded;
import com.encurtador.repositorys.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @InjectMocks
    UrlService urlService;
    @Mock
    UrlRepository urlRepository;

    String newUrl = "new.com.br";
    String oldUrl = "old.com.br";
    UrlDto urlDtoNew = new UrlDto(newUrl);;
    UrlDto oldUrlDto = new UrlDto(oldUrl);
    Url url = new Url(null, oldUrl, "XYZABC", LocalDateTime.now(), LocalDateTime.now().plusDays(5));
    Url urlExpired = new Url(null,"expired.com.br","expired",LocalDateTime.now(),LocalDateTime.now().minusDays(10));

    @Test
    public void encurtarUrlShouldReturnUrlDtoWithShortUrl () throws Exception {
        Mockito.when(urlRepository.findByUrlOriginal(newUrl)).thenReturn(Optional.empty());
        UrlDto response = urlService.encutarUrl(urlDtoNew);
        Assertions.assertTrue(response.url().startsWith("http://localhost:8080/"));
        Mockito.verify(urlRepository,Mockito.times(1)).findByUrlOriginal(newUrl);
        Mockito.verify(urlRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void encurtarUrlShouldReturnExistingUrlDtoWithShortUrl () throws Exception {
        Mockito.when(urlRepository.findByUrlOriginal(oldUrl)).thenReturn(Optional.of(url));
        UrlDto response = urlService.encutarUrl(oldUrlDto);
        Assertions.assertTrue(response.url().startsWith("http://localhost:8080/"));
        Assertions.assertEquals(response.url().split("http://localhost:8080/")[1],"XYZABC");
        Mockito.verify(urlRepository,Mockito.times(1)).findByUrlOriginal(oldUrl);
        Mockito.verify(urlRepository, Mockito.times(0)).save(Mockito.any());
    }


    @Test
    public void encontrarUrlOriginalShouldReturnUrlDtoWithUrlOriginalWhenUrlDtoIsValid(){
        Mockito.when(urlRepository.findByShortUrl("XYZABC")).thenReturn(Optional.of(url));

     UrlDto result = urlService.encontrarUrlOriginal("XYZABC");
        Assertions.assertEquals(result.url(), oldUrl);
    Mockito.verify(urlRepository,Mockito.times(1)).findByShortUrl("XYZABC");
    }
    @Test
    public void encontrarUrlOriginalShouldThrowUrlNotFoundedWhenUrlRequestIsNotValid(){
        Mockito.when(urlRepository.findByShortUrl("FALSE")).thenReturn(Optional.empty());
        Assertions.assertThrows(UrlNotFounded.class, () -> {
            urlService.encontrarUrlOriginal("FALSE");
        });
        Mockito.verify(urlRepository,Mockito.times(1)).findByShortUrl("FALSE");
    }
    @Test
    public void encontrarUrlOriginalShouldThrowUrlNotFoundedWhenShortUrlisExpired(){
        Mockito.when(urlRepository.findByShortUrl("expired")).thenReturn(Optional.of(urlExpired));
        Assertions.assertThrows(UrlExpired.class, () -> {
            urlService.encontrarUrlOriginal("expired");
        });
        Mockito.verify(urlRepository,Mockito.times(1)).findByShortUrl("expired");
    }

}

