package com.kekfeed.jwt.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

public abstract class AbstractMemeBatchApi {

    protected static final String MEME_BATCH_RESOURCE_URL = "http://batch-generator-api-service-dev:80/meme/batch/%d/%d";
    @Autowired
    private RestTemplate restTemplate;

    protected MemesBatch testDiscovery(Integer from, Integer to) {
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
