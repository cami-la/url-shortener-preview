package dev.camila.url.shortener.preview.service;

import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


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
    Mockito.when(this.urlRepository.findByShortUrl(shortUrl))
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
    Mockito.when(this.urlRepository.findByShortUrl(shortUrl))
        .thenReturn(Optional.empty());
    // when + then
    RuntimeException actual = Assertions.assertThrows(RuntimeException.class,
        () -> this.urlService.getOriginalUrlByShortUrl(shortUrl));
    Assertions.assertEquals(String.format("'%s' not found", shortUrl), actual.getMessage());
  }

  @Test
  void shouldFindOrSaveUrlWhenUrlExistsInDatabase() {
    //given
    String originalUrl = "https://www.example.com/";
    String shortUrl = "xyz123";
    Url expected = Url.builder()
        .originalUrl(originalUrl)
        .shortUrl(shortUrl)
        .build();
    Mockito.when(this.urlRepository.findByOriginalUrl(ArgumentMatchers.eq(originalUrl)))
        .thenReturn(Optional.of(expected));
    //when
    Url actual = this.urlService.findOrSaveUrl(originalUrl);
    //then
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void shouldFindOrSaveUrlWhenUrlDoesNotExistInDatabase() {
    //given
    String originalUrl = "https://www.example.com/";
    String shortUrl = "xyz123";
    Url expected = Url.builder()
        .originalUrl(originalUrl)
        .shortUrl(shortUrl)
        .build();
    Mockito.when(this.urlRepository.findByOriginalUrl(ArgumentMatchers.eq(originalUrl)))
        .thenReturn(Optional.empty());
    Mockito.when(this.urlRepository.save(ArgumentMatchers.any(Url.class)))
        .thenReturn(expected);
    //when
    Url actual = this.urlService.findOrSaveUrl(originalUrl);
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
