package dev.camila.url.shortener.preview.resource;

import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.service.UrlService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UrlResourceTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UrlService urlService;

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


}
