package org.geroba.groovex.money

import javax.money.CurrencyUnit
import javax.money.Monetary

/**
 * @author Gernot R. Bauer
 *
 * @since 1.0
 */
interface GroovexCurrencies {
    static final CurrencyUnit EUR = Monetary.getCurrency("EUR")
    static final CurrencyUnit USD = Monetary.getCurrency("USD")
}
