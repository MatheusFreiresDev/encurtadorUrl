package com.encurtador.service;
import com.encurtador.dtos.UrlDto;
import com.encurtador.Entity.Url;
import com.encurtador.execeptions.UrlExpired;
import com.encurtador.execeptions.UrlNotFounded;
import com.encurtador.repositorys.UrlRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlDto encutarUrl (UrlDto urlRequest) throws  Exception{
       Optional<Url> urlExistente= urlRepository.findByUrlOriginal(urlRequest.url());
       if(urlExistente.isPresent()) {
           return new UrlDto("http://localhost:8080/" + urlExistente.get().getShortUrl());
    }
        Url urlCriada = new Url();
       LocalDateTime createdAt = LocalDateTime.now();
       LocalDateTime expiryAt = returnDateToExpires(createdAt);
       urlCriada.setUrlOriginal(urlRequest.url());
       urlCriada.setShortUrl(returnShortUrl());
       urlCriada.setCreatedAt(createdAt);
       urlCriada.setExpiryAt(expiryAt);
       urlRepository.save(urlCriada);
    return new UrlDto("http://localhost:8080/" + urlCriada.getShortUrl());
    }

    public UrlDto encontrarUrlOriginal( String urlrequest ){
        Optional<Url> urlDoBanco = urlRepository.findByShortUrl(urlrequest);

        if(!urlDoBanco.isPresent()){
            throw new UrlNotFounded("Url Nao foi encotrada.");
        }
        if (urlDoBanco.get().getExpiryAt().isBefore(LocalDateTime.now())) {
            throw new UrlExpired("Este link expirou.");
        }
        return new UrlDto( urlDoBanco.get().getUrlOriginal());
    }




    public String returnShortUrl (){
        String codigoCurto = UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 6);
    return codigoCurto;
    }

    public LocalDateTime returnDateToExpires (LocalDateTime created){
        return created.plusDays(5);
    }

}

