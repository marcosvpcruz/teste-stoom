package br.com.stoom.store.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ")
    @Column(name = "id")
    private Long id;
    
	@Column(name = "sku")
    private String sku;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "data_inclusion")
    private LocalDateTime dataInclusion;
    
    @Column(name = "data_exclusion")
    private LocalDateTime dataExclusion;
    
    @Column(name = "data_change")
    private LocalDateTime dataChange;
    
    @Column(name = "price")
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name="brand_id", nullable=false)
    @JsonIgnore
    private Brand brand;
    
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    @JsonIgnore
    private Category category;
}