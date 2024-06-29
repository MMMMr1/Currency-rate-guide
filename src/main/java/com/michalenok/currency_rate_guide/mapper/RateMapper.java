package com.michalenok.currency_rate_guide.mapper;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.model.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RateMapper {

    Rate rateResponseToRate(RateResponse rateResponse);

}