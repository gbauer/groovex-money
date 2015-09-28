package org.geroba.groovex.money

import spock.lang.Specification
import spock.lang.Unroll

import javax.money.MonetaryAmount

import static org.geroba.groovex.money.GroovexCurrencies.EUR

/**
 * @author Gernot R. Bauer
 *
 * @since 1.0
 */
class NumberMonetaryValueExtensionTest extends Specification {

    @Unroll
    def "'of' method automatically converts to monetary value for type #number.class"(Number number) {
        when:
        MonetaryAmount result = number.of EUR

        then:
        result != null
        result.number == number
        result.currency = EUR

        where:
        number << [new Integer(1),
                   new Float(1),
                   new Double(1),
                   new Byte(1),
                   new BigInteger(1),
                   new BigDecimal(1)]
    }

}
