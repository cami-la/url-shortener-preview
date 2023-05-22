package dev.camila.url.shortener.preview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Data
public class Url {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String originalUrl;
  private String shortUrl;
}
