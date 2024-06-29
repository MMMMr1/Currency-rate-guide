package com.michalenok.currency_rate_guide.repository;

import com.michalenok.currency_rate_guide.model.entity.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {
    @Query("select c from Currency c where c.curCode = ?1 and ?2 between c.curDateStart and c.curDateEnd")
    Currency findByCurCode(String curCode, Date ondate);
    @Query("select c from Currency c where c.curAbbreviation = ?1 and ?2 between c.curDateStart and c.curDateEnd")
    Currency findByCurAbbreviation(String curAbbreviation, Date ondate);
}
