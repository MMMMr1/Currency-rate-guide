package com.michalenok.currency_rate_guide.repository;

import com.michalenok.currency_rate_guide.model.entity.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {
    @Query("select c from Currency c where c.curCode = ?1 and ?2 between c.curDateStart and c.curDateEnd")
    Optional <Currency> findByCurCode(String curCode, LocalDate ondate);
    @Query("select c from Currency c where c.curAbbreviation = ?1 and ?2 between c.curDateStart and c.curDateEnd")
    Optional <Currency> findByCurAbbreviation(String curAbbreviation, LocalDate ondate);
}
