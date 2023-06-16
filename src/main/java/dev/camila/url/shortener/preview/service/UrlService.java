package dev.camila.url.shortener.preview.service;

import com.google.common.hash.Hashing;
import dev.camila.url.shortener.preview.exceptions.BusinessException;
import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public record UrlService(
    UrlRepository urlRepository
) {
  private static final List<Character> ALLOWED_CHARS = Arrays.asList(
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
      'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
      'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
      'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', '+'
  );

  /**
   * Método responsável por encontrar a URL correspondente da URL encurtada
   *
   * @param shortUrl "URL encurtada que será pesquisada no banco de dados"
   * @return uma String contendo a URL original
   */
  public Url getOriginalUrlByShortUrl(String shortUrl) {
    return this.urlRepository.findByShortUrl(shortUrl)
        .orElseThrow(() -> new BusinessException(String.format("'%s' not found", shortUrl)));
  }

  /**
   * Método responsável por encontrar o objeto ou salvá-lo e retornar o mesmo.
   *
   * @param originalUrl "Url que será encontrada ou atualizada"
   * @return O objeto URL salvo no banco de dados
   */
  public Url findOrSaveUrl(String originalUrl) {
    Url url;
    Optional<Url> optionalUrl = this.urlRepository().findByOriginalUrl(originalUrl);
    if (optionalUrl.isPresent()) {
      url = optionalUrl.get();
    } else {
      Url urlToSave = Url.builder()
          .originalUrl(originalUrl)
          .shortUrl(generateShortUrl(originalUrl))
          .build();
      url = this.urlRepository.save(urlToSave);
    }
    return url;
  }

  /**
   * Método responsável por gerar o hash da URL encurtada
   *
   * @param originalUrl "Url a qual será encurtada"
   * @return Um hash referente a originalUrl que foi convertida usando sha256
   * @see "https://github.com/google/guava/wiki/HashingExplained"
   * @see "https://www.freecodecamp.org/portuguese/news/md5-x-sha-1-x-sha-2-qual-e-o-hash-de-criptografia-mais-seguro-e-como-verifica-lo/"
   */
  public static String generateShortUrl(String originalUrl) {
    byte[] hash = Hashing.sha256()
        .hashString(originalUrl, StandardCharsets.UTF_8)
        .asBytes();
    StringBuilder shortUrl = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      int index = hash[i] & 0xFF;
      shortUrl.append(ALLOWED_CHARS.get(index % ALLOWED_CHARS.size()));
    }
    return shortUrl.toString();
  }
}
