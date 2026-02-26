package com.encurtador.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Entity
@Table(name = "urls")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String urlOriginal;
    private String shortUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiryAt;

    public Url(String urlOriginal, String shortUrl, LocalDateTime createdAt, LocalDateTime expiryAt) {
        this.urlOriginal = urlOriginal;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
        this.expiryAt = expiryAt;
    }

}
