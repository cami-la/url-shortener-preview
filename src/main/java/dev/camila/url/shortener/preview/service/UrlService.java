package dev.camila.url.shortener.preview.service;

import com.google.common.hash.Hashing;
import dev.camila.url.shortener.preview.model.Url;
import dev.camila.url.shortener.preview.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public record UrlService(
    UrlRepository urlRepository
) {
  private static final List<Character> ALLOWED_CHARS = Arrays.asList(
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
      'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
      'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
      'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
  );

  /**
   * Método responsável por encontrar a URL correspondente da URL encurtada
   * @param shortUrl "URL encurtada que será pesquisada no banco de dados"
   * @return uma String contendo a URL original
   */
  public Url getOriginalUrlByShortUrl(String shortUrl) {
    return this.urlRepository.findByShortUrl(shortUrl)
        .orElseThrow(() -> new RuntimeException(String.format("'%s' not found", shortUrl)));
  }

  /**
   * Método responsável por montar o objeto da URL que será salva e retornar a mesma.
   * @param originalUrl "Url que será salva ou atualizada"
   * @return O objeto URL salvo no banco de dados
   */
  public Url saveOrUpdateUrl(String originalUrl) {
    Url url = this.urlRepository.findByOriginalUrl(originalUrl)
        .orElseGet(() -> Url.builder()
            .originalUrl(originalUrl)
            .shortUrl(generateShortUrl(originalUrl))
            .build());
    return this.urlRepository.save(url);
  }

  /**
   * Método responsável por gerar o hash da URL encurtada
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
