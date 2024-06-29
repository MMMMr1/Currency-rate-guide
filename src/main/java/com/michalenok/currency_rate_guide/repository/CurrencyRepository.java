package com.michalenok.currency_rate_guide.repository;

import com.michalenok.currency_rate_guide.model.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {

}
