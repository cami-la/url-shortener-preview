package dev.camila.url.shortener.preview.repository;

import dev.camila.url.shortener.preview.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {
  Optional<Url> findByShortUrl(String shortUrl);
  Optional<Url> findByOriginalUrl(String originalUrl);
}
