package com.michalenok.currency_rate_guide.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "rates")
@IdClass(RateId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rate {
    @Id
    private String curId;
    @Id
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private String curOfficialRate;

}
