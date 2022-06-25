package io.oferto.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.oferto.microservice.model.Stock;

public interface StockRepository  extends JpaRepository<Stock, Long> {

}
