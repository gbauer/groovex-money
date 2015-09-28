package org.geroba.groovex.money.extensions

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
        result.currency == EUR

        where:
        number << [new Integer(1),
                   new Float(1),
                   new Double(1),
                   Byte.valueOf((byte) 1),
                   new BigInteger(1),
                   new BigDecimal(1)]
    }

    @Unroll
    def "Multiply scalar #scalar with monetary amount #amount results in #expected"(Number scalar, MonetaryAmount amount, MonetaryAmount expected) {
        when:
        MonetaryAmount result = scalar * amount

        then:
        result == expected

        where:
        scalar | amount      || expected
        1      | 1.euros()   || 1.euros()
        2      | 1.euros()   || 2.euros()
        -1     | 1.euros()   || -1.euros()
        0      | 100.euros() || 0.euros()
        100    | 0.euros()   || 0.euros()
        1.2    | 2.euros()   || 2.4.euros()
    }
}
