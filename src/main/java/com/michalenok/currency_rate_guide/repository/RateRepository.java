package com.michalenok.currency_rate_guide.repository;


import com.michalenok.currency_rate_guide.model.entity.Rate;
import com.michalenok.currency_rate_guide.model.entity.RateId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, RateId> {
}
