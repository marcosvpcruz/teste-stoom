package br.com.stoom.store.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

	private Long id;
    private String sku;
    private String name;
    private LocalDateTime dataInclusion;
    private LocalDateTime dataExclusion;
    private LocalDateTime dataChange;
    private BigDecimal price;
    private Long brandId;
    private Long categoryId;
}
