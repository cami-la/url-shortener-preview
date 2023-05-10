package dev.camila.url.shortener.preview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerPreviewApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerPreviewApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
