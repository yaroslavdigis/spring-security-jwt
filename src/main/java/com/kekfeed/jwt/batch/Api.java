package com.kekfeed.jwt.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("meme-batch")
@RequiredArgsConstructor
public class Api {

    private static final String MEME_BATCH_RESOURCE_URL = "http://batch-generator-api-service-dev:80/meme/batch/%d/%d";
    private final RestTemplate restTemplate;

    @GetMapping("/test/{from}/{to}")
    public MemesBatch testDiscovery(@PathVariable Integer from, @PathVariable Integer to) {
        try {
            return ofNullable(
                    restTemplate.postForEntity(
                            format(MEME_BATCH_RESOURCE_URL, from, to), null, MemesBatch.class
                    ).getBody()
            ).orElseThrow(() -> new MemesBatchException("Received MemesBatch from batch-generator is null"));
        } catch (RestClientException e) {
            throw new MemesBatchException("Invalid response from batch-generator, cause: " + e.getMessage());
        }
    }
}
