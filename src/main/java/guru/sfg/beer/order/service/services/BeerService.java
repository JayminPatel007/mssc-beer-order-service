package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerByUpc(String upc);
    BeerDto getBeerById(UUID beerId);
}
