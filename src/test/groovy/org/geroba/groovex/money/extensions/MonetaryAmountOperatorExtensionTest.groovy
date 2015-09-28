package org.geroba.groovex.money.extensions

import spock.lang.Specification
import spock.lang.Unroll

import javax.money.MonetaryAmount

import static org.geroba.groovex.money.GroovexCurrencies.EUR

/**
 * @author gernot.r.bauer
 */
class MonetaryAmountOperatorExtensionTest extends Specification {

    def "Negate results in negative value"() {
        when:
        def firstValue = (-10).of EUR
        def secondValue = -10.euros()

        then:
        firstValue == secondValue
    }

    def "Postivie results in positive value"() {
        when:
        def firstValue = (+10).of EUR
        def secondValue = +10.euros()

        then:
        firstValue == secondValue
    }

    @Unroll
    def "Plus operators adds #first + #second = #expected"(MonetaryAmount first, MonetaryAmount second, MonetaryAmount expected) {
        when:
        def sum = first + second

        then:
        sum == expected

        where:
        first       | second     || expected
        10.euros()  | 9.euros()  || 19.euros()
        10.euros()  | 0.euros()  || 10.euros()
        0.euros()   | 10.euros() || 10.euros()
        -10.euros() | 9.euros()  || -1.euros()
    }

    @Unroll
    def "Minus operators substracts #first - #second = #expected"(MonetaryAmount first, MonetaryAmount second, MonetaryAmount expected) {
        when:
        def sum = first - second

        then:
        sum == expected

        where:
        first       | second     || expected
        10.euros()  | 9.euros()  || 1.euros()
        10.euros()  | 0.euros()  || 10.euros()
        0.euros()   | 10.euros() || -10.euros()
        -10.euros() | 9.euros()  || -19.euros()
    }

    @Unroll
    def "Multiply monetary amount #amount with scalar #scalar results in #expected"(MonetaryAmount amount, Number scalar, MonetaryAmount expected) {
        when:
        MonetaryAmount result = amount * scalar

        then:
        result == expected

        where:
        amount      | scalar || expected
        1.euros()   | 1      || 1.euros()
        1.euros()   | 2      || 2.euros()
        1.euros()   | -1     || -1.euros()
        100.euros() | 0      || 0.euros()
        0.euros()   | 100    || 0.euros()
        2.euros()   | 1.2    || 2.4.euros()
    }

    @Unroll
    def "Divide monetary amount #amount by scalar #scalar results in #expected"(MonetaryAmount amount, Number scalar, MonetaryAmount expected) {
        when:
        MonetaryAmount result = amount / scalar

        then:
        result == expected

        where:
        amount      | scalar || expected
        1.euros()   | 1      || 1.euros()
        1.euros()   | 2      || 0.5.euros()
        1.euros()   | -1     || -1.euros()
        0.euros()   | 100    || 0.euros()
        2.euros()   | 0.5    || 4.euros()
    }

}
