package br.com.stoom.store.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ")
    @Column(name = "id")
    private Long id;
    
    private String name;

    @Column(name = "data_inclusion")
    private LocalDateTime dataInclusion;
    
    @Column(name = "data_exclusion")
    private LocalDateTime dataExclusion;
    
    @Column(name = "data_change")
    private LocalDateTime dataChange;
    
    @OneToMany(mappedBy="brand")
    @JsonIgnore
    private List<Product> products;
}
