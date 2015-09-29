package org.geroba.groovex.money.extensions

import groovy.transform.TypeChecked
import org.geroba.groovex.money.GroovexCurrencies

import javax.money.CurrencyUnit
import javax.money.MonetaryAmount

import static org.geroba.groovex.money.MoneyFactory.monetaryAmount

/**
 * @author Gernot R. Bauer
 *
 * @since 1.0
 */
@TypeChecked
class NumberMonetaryValueExtension {

    static MonetaryAmount of(Number amount, CurrencyUnit currency) {
        monetaryAmount(amount, currency)
    }

    static MonetaryAmount euro(Number self) {
        monetaryAmount(self as BigDecimal, GroovexCurrencies.EUR)
    }

    static MonetaryAmount dollar(Number self) {
        monetaryAmount(self as BigDecimal, GroovexCurrencies.USD)
    }

    static MonetaryAmount multiply(Number self, MonetaryAmount monetaryAmount) {
        monetaryAmount.multiply(self)
    }
}
