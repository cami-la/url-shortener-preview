package dev.camila.url.shortener.preview;

import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/url")
public record UrlController(
  UrlService urlService
) {

  @GetMapping("/{shortUrl}")
  public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) throws URISyntaxException {
    Url url = this.urlService.getOriginalUrlByShortUrl(shortUrl);
    String redirectTo = url.getOriginalUrl();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(new URI(redirectTo));
    return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
  }

  @PostMapping
  public ResponseEntity<Url> save(@RequestParam(value = "originalUrl") String originalUrl) {
    Url url = this.urlService.saveOrUpdateUrl(originalUrl);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{/shortUrl}")
        .buildAndExpand(url.getShortUrl())
        .toUri();
    return ResponseEntity.created(uri).body(url);
  }
}
