package com.spring.start.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.money.CurrencyUnit;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Getter @Setter
@Embeddable
public class Money {

    @Transient
    private static final String DEFAULT_CURRENCY = "PLN";

    BigDecimal amount;

    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentCurrencyUnit")
    CurrencyUnit currency;

    public Money(BigDecimal amount, String currency) {
        org.javamoney.moneta.Money money = org.javamoney.moneta.Money.of(amount, currency);
        new Money(money);
    }

    public Money(org.javamoney.moneta.Money money) {
        this.amount = money.getNumberStripped();
        this.currency = money.getCurrency();
    }

    public Money() {
        org.javamoney.moneta.Money money = org.javamoney.moneta.Money.of(BigDecimal.ZERO, DEFAULT_CURRENCY);
        new Money(money);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
