package dev.camila.url.shortener.preview.repository;

import dev.camila.url.shortener.preview.model.Url;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UrlRepositoryTest {
  @Autowired
  private UrlRepository urlRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  private Url url1;
  private Url url2;

  @BeforeEach
  void setUp() {
    url1 = testEntityManager.persistAndFlush(Url.builder()
        .originalUrl("https://www.linkedin.com/in/cami-la/")
        .shortUrl("abc123")
        .build());
    url2 = testEntityManager.persistAndFlush(Url.builder()
        .originalUrl("https://github.com/cami-la")
        .shortUrl("xyz123")
        .build());
  }

  @AfterEach
  void tearDown() {
    testEntityManager.clear();
  }

  @Test
  void shouldFindUrlByShortUrl() {
    //given
    String shortUrl = "abc123";
    //when
    Optional<Url> optionalActual = this.urlRepository.findByShortUrl("abc123");
    String actual = optionalActual.get().getOriginalUrl();
    //then
    String expected = "https://www.linkedin.com/in/cami-la/";
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void shouldReturnOptionalEmptyWhenShortUrlNotFound() {
    //given
    String shortUrl = "abc124";
    //when
    Optional<Url> optionalUrl = this.urlRepository.findByShortUrl(shortUrl);
    // then
    Assertions.assertTrue(optionalUrl.isEmpty());
  }

  @Test
  void shouldfindUrlByOriginalUrl() {
    //given
    String originalUrl = "https://github.com/cami-la";
    //when
    Optional<Url> optionalActual = this.urlRepository.findByOriginalUrl(originalUrl);
    String actual = optionalActual.get().getShortUrl();
    //then
    String expected = "xyz123";
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void shouldReturnOptionalEmptyWhenOriginalUrlNotFound(){
    //given
    String originalUrl = "https://github.com/cami-la2";
    //when
    Optional<Url> optionalUrl = this.urlRepository.findByOriginalUrl(originalUrl);
    // then
    Assertions.assertTrue(optionalUrl.isEmpty());
  }
}