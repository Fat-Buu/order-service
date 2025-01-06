package com.techno.microservices.order.config;

import com.techno.microservices.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient() {
        RestClient restClient = RestClient.builder().baseUrl(inventoryServiceUrl).build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyService = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyService.createClient(InventoryClient.class);
    }
}
