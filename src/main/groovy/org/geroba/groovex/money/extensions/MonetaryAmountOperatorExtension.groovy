package org.geroba.groovex.money.extensions

import groovy.transform.TypeChecked

import javax.money.MonetaryAmount

/**
 * @author Gernot R. Bauer
 *
 * @since 1.0
 */
@TypeChecked
class MonetaryAmountOperatorExtension {

    static MonetaryAmount negative(MonetaryAmount self) {
        self.negate()
    }

    static MonetaryAmount positive(MonetaryAmount self) {
        self.plus()
    }

    static MonetaryAmount plus(MonetaryAmount self, MonetaryAmount other) {
        self.add(other)
    }

    static MonetaryAmount minus(MonetaryAmount self, MonetaryAmount other) {
        self.subtract(other)
    }

    static MonetaryAmount div(MonetaryAmount self, Number divisor) {
        self.divide(divisor)
    }

    static Number div(MonetaryAmount self, MonetaryAmount divisor) {
        self.number.toBigDecimal() / divisor.number.toBigDecimal()
    }
}
