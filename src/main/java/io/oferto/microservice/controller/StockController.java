package io.oferto.microservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.oferto.microservice.common.ProductDto;
import io.oferto.microservice.common.StockDto;
import io.oferto.microservice.exception.StockNotFoundException;
import io.oferto.microservice.model.Stock;
import io.oferto.microservice.proxy.ProductProxy;
import io.oferto.microservice.repository.StockRepository;

@RestController
public class StockController {
	Logger log = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
    private StockRepository stockRepository;
				
	@Autowired
	private ProductProxy productProxy;
		
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/stocks")
    List<StockDto> findAll() {
        List<Stock> stocks = stockRepository.findAll();
        
        return stocks.stream()
          .map(this::convertToDto)
          .collect(Collectors.toList());
    }
    
    @GetMapping("/stocks/{id}")
    StockDto findOne(@PathVariable Long id) {  
    	Stock stock = stockRepository.findById(id)
                 .orElseThrow(() -> new StockNotFoundException(id));
         
        StockDto stockDto = convertToDto(stock);
         
        ProductDto productDto = productProxy.getProduct(
				stock.getProductId());
        
        stockDto.setProduct(productDto);
        
    	return stockDto;
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/stocks")
    StockDto save(@RequestBody StockDto stockDto) {
    	Stock stock = convertToEntity(stockDto);
    	
    	Stock stockCreated = stockRepository.save(stock);
        
    	return convertToDto(stockCreated);
    }
    
    @DeleteMapping("/stocks/{id}")
    void delete(@PathVariable Long id) {
        stockRepository.deleteById(id);
    }
    
    private StockDto convertToDto(Stock stock) {
    	StockDto stockDto = modelMapper.map(stock, StockDto.class);
    	    	
    	return stockDto;
    }
    
    private Stock convertToEntity(StockDto stockDto) throws ParseException {
    	Stock stock = modelMapper.map(stockDto, Stock.class);    	
    	stock.setProductId(stockDto.getProductId());
        
        return stock;
    }
}
