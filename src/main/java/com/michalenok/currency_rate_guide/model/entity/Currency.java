package com.michalenok.currency_rate_guide.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "currencies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Currency {
    @Id
    private String curId;
    private String curCode;
    private String curAbbreviation;
    private String curScale;
    private String curName;
    @Temporal(TemporalType.DATE)
    private Date curDateStart;
    @Temporal(TemporalType.DATE)
    private Date curDateEnd;
}
