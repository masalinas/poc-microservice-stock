package io.oferto.microservice.exception;

public class StockNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockNotFoundException(Long id) {
        super("Stock id not found : " + id);
    }
}
