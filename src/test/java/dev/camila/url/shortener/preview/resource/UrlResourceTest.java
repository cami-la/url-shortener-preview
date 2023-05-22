package dev.camila.url.shortener.preview.resource;

import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.repository.UrlRepository;
import dev.camila.url.shortener.preview.service.UrlService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UrlResourceTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UrlService urlService;
  @Autowired
  private UrlRepository urlRepository;
  private static String URL = "/";

  private Url url1;
  private Url url2;

  @BeforeEach
  void setUp() {
    urlRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    urlRepository.deleteAll();
  }

  @Test
  void shouldCreateShortUrlAndReturn201StatusCode() throws Exception {
    //given
    String originalUrl = "https://www.linkedin.com/in/cami-la/";
    //when
    //then
    mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("originalUrl", originalUrl)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.originalUrl").value(originalUrl))
        .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl").isNotEmpty())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void shouldUpdateOriginalUrlAndReturn201StatusCode() throws Exception {
    //given
    urlRepository.saveAndFlush(Url.builder()
        .originalUrl("https://www.linkedin.com/in/cami-la/")
        .shortUrl("abc123")
        .build());
    String originalUrl = "https://www.linkedin.com/in/cami-la/";
    //when
    //then
    mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("originalUrl", originalUrl)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.originalUrl").value(originalUrl))
        .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl").value("abc123"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void shouldRedirectToOriginalUrl() throws Exception {
    //given
    urlRepository.saveAndFlush(Url.builder()
        .originalUrl("https://www.linkedin.com/in/cami-la/")
        .shortUrl("abc123")
        .build());
    String shorlUrl = "abc123";
    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL + "{shortUrl}", shorlUrl))
        .andExpect(MockMvcResultMatchers.status().isMovedPermanently())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void shouldReturnNotFoundWhenRedirectingToInvalidShortUrl() throws Exception {
    //given
    String shorlUrl = "abc122";
    //when
    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL + "{shortUrl}", shorlUrl))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.exception")
                .value("class dev.camila.url.shortener.preview.exceptions.BusinessException")
        )
        .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty())
        .andDo(MockMvcResultHandlers.print());
  }
}
