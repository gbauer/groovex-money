package org.geroba.groovex.money.extensions

import spock.lang.Specification
import spock.lang.Unroll

import javax.money.MonetaryAmount
import javax.money.MonetaryException

import static org.geroba.groovex.money.GroovexCurrencies.EUR

/**
 * @author gernot.r.bauer
 */
class MonetaryAmountOperatorExtensionTest extends Specification {

    def "Negate results in negative value"() {
        when:
        def firstValue = (-10).of EUR
        def secondValue = -10.euro()

        then:
        firstValue == secondValue
    }

    def "Postivie results in positive value"() {
        when:
        def firstValue = (+10).of EUR
        def secondValue = +10.euro()

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
        first      | second    || expected
        10.euro()  | 9.euro()  || 19.euro()
        10.euro()  | 0.euro()  || 10.euro()
        0.euro()   | 10.euro() || 10.euro()
        -10.euro() | 9.euro()  || -1.euro()
    }

    @Unroll
    def "Plus operator cannot add value #first and #second as they are of different currencies"(MonetaryAmount first, MonetaryAmount second) {
        when:
        def sum = first + second

        then:
        thrown MonetaryException

        where:
        first      | second
        1.euro()   | 1.dollar()
        1.dollar() | 1.euro()
    }

    @Unroll
    def "Minus operators substracts #first - #second = #expected"(MonetaryAmount first, MonetaryAmount second, MonetaryAmount expected) {
        when:
        def sum = first - second

        then:
        sum == expected

        where:
        first      | second    || expected
        10.euro()  | 9.euro()  || 1.euro()
        10.euro()  | 0.euro()  || 10.euro()
        0.euro()   | 10.euro() || -10.euro()
        -10.euro() | 9.euro()  || -19.euro()
    }

    @Unroll
    def "Multiply monetary amount #amount with scalar #scalar results in #expected"(MonetaryAmount amount, Number scalar, MonetaryAmount expected) {
        when:
        MonetaryAmount result = amount * scalar

        then:
        result == expected

        where:
        amount     | scalar || expected
        1.euro()   | 1      || 1.euro()
        1.euro()   | 2      || 2.euro()
        1.euro()   | -1     || -1.euro()
        100.euro() | 0      || 0.euro()
        0.euro()   | 100    || 0.euro()
        2.euro()   | 1.2    || 2.4.euro()
    }

    @Unroll
    def "Divide monetary amount #amount by scalar #scalar results in #expected"(MonetaryAmount amount, Number scalar, MonetaryAmount expected) {
        when:
        MonetaryAmount result = amount / scalar

        then:
        result == expected

        where:
        amount   | scalar || expected
        1.euro() | 1      || 1.euro()
        1.euro() | 2      || 0.5.euro()
        1.euro() | -1     || -1.euro()
        0.euro() | 100    || 0.euro()
        2.euro() | 0.5    || 4.euro()
    }

    @Unroll
    def "Divide monetary amount #dividend by monetary amount #divisor results in scalar #expected"(MonetaryAmount dividend, MonetaryAmount divisor, Number expected) {
        when:
        Number result = dividend / divisor

        then:
        result == expected

        where:
        dividend | divisor    || expected
        1.euro() | 1.euro()   || 1
        1.euro() | 2.euro()   || 0.5
        1.euro() | -1.euro()  || -1
        0.euro() | 10.euro()  || 0
        2.euro() | 0.5.euro() || 4
    }
}
