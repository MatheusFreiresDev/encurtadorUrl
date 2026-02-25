package com.encurtador.controllers;

import com.encurtador.Entity.Url;
import com.encurtador.dtos.UrlDto;
import com.encurtador.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/encurtar")
    public ResponseEntity<UrlDto> encurtarUrl(@RequestBody @Valid UrlDto urlDto) throws Exception {
            UrlDto urlEncurtada = urlService.encutarUrl(urlDto);
            return ResponseEntity.ok(urlEncurtada);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirecionarUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        UrlDto url = urlService.encontrarUrlOriginal(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", url.url())
                .build();
    }
}