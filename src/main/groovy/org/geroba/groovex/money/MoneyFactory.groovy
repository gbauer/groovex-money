package org.geroba.groovex.money

import groovy.transform.CompileStatic
import org.javamoney.moneta.Money

import javax.money.CurrencyUnit
import javax.money.MonetaryAmount

/**
 * @author gernot.r.bauer
 */
@CompileStatic
final class MoneyFactory {

    static MonetaryAmount monetaryAmount(Number value, CurrencyUnit currency) {
        Money.of(value, currency)
    }
}
