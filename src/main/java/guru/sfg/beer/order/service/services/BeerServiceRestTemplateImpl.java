package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BeerServiceRestTemplateImpl implements BeerService {
    private static final String BEER_BY_UPC_PATH = "/api/v1/beer/upc/{upc}";
    private static final String BEER_BY_ID_PATH = "/api/v1/beer/{beerId}";
    private final RestTemplate restTemplate;
    private String beerServiceHostname;

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setBeerServiceHostName(String beerServiceHostName) {
        this.beerServiceHostname = beerServiceHostName;
    }

    @Override
    public BeerDto getBeerByUpc(String upc) {

        ResponseEntity<BeerDto> response = restTemplate.exchange(
                beerServiceHostname + BEER_BY_UPC_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                upc
        );

        return response.getBody();
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        ResponseEntity<BeerDto> response = restTemplate.exchange(
                beerServiceHostname + BEER_BY_ID_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                beerId
        );

        return response.getBody();
    }
}
