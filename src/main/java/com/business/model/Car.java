package com.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;
    private String maker;
    private String model;
    private Integer year;
    private String color;
    private String licensePlate;
    private BigDecimal dailyPrice;
    private Boolean isRented;
}
