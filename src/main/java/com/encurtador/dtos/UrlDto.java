package com.encurtador.dtos;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
public record UrlDto(  @NotBlank(message = "URL não pode ser vazia") String url) {
}
