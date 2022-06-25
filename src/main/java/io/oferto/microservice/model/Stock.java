package io.oferto.microservice.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    private Long productId;
          
    @NotNull
    private BigDecimal quantity;
        
    private String lot;
    
    private Date expirationDate;
}
