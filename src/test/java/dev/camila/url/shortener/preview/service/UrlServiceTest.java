package dev.camila.url.shortener.preview.service;

import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
  @Mock
  private UrlRepository urlRepository;

  @InjectMocks
  private UrlService urlService;


  @Test
  void shouldGetOriginalUrlByShortUrl() {
    //given
    String originalUrl = "https://www.example.com/";
    String shortUrl = "xyz123";

    Url expected = Url.builder()
        .originalUrl(originalUrl)
        .shortUrl(shortUrl)
        .build();

    when(this.urlRepository.findByShortUrl(shortUrl))
        .thenReturn(Optional.of(expected));
    //when
    Url actual = this.urlService.getOriginalUrlByShortUrl(shortUrl);
    //then
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void shouldThrowExceptionWhenShortUrlNotFound() {
    // given
    String shortUrl = "xyz123";
    when(this.urlRepository.findByShortUrl(shortUrl))
        .thenReturn(Optional.empty());
    // when + then
    RuntimeException actual = Assertions.assertThrows(RuntimeException.class,
        () -> this.urlService.getOriginalUrlByShortUrl(shortUrl));
    Assertions.assertEquals(String.format("'%s' not found", shortUrl), actual.getMessage());
  }

  @Test
  void shouldSaveOrUpdateUrl() {
    //given
    String originalUrl = "https://www.example.com/";
    String shortUrl = "xyz123";

    Url expected = Url.builder()
        .originalUrl(originalUrl)
        .shortUrl(shortUrl)
        .build();

    when(this.urlRepository.findByOriginalUrl(originalUrl))
        .thenReturn(Optional.of(expected))
        .thenReturn(Optional.empty());
    when(this.urlRepository.save(any(Url.class))).thenReturn(expected);
    //when
    Url actual = this.urlService.saveOrUpdateUrl(originalUrl);
    //then
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void shouldGenerateHashToShortUrl() {
    //given
    String originalUrl = "https://www.example.com/";
    //when
    String actual = UrlService.generateShortUrl(originalUrl);
    //then
    int expected = 6;
    Assertions.assertEquals(expected, actual.length());
  }
}
