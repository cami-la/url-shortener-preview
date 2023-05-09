package dev.camila.url.shortener.preview.resource;

import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/urls")
public record UrlResource(
  UrlService urlService
) {

  @GetMapping("/{shortUrl}")
  public ResponseEntity<Url> redirectToOriginalUrl(@PathVariable String shortUrl) throws URISyntaxException {
    Url urlByShortUrl = this.urlService.getOriginalUrlByShortUrl(shortUrl);
    String redirectTo = urlByShortUrl.getOriginalUrl();
    URI uri = new URI(redirectTo);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(uri);
    return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
  }

  @PostMapping
  public ResponseEntity<Url> createShortUrlFromOriginalUrl(@RequestParam(value = "originalUrl") String originalUrl) {
    Url url = this.urlService.saveOrUpdateUrl(originalUrl);
    return ResponseEntity.status(HttpStatus.CREATED).body(url);
  }
}
