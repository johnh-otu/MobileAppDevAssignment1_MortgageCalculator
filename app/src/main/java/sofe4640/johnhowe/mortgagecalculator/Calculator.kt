package sofe4640.johnhowe.mortgagecalculator

import kotlin.math.pow

class Calculator {

    fun getMortgagePaymentAmount(principal: Float, interest: Float, numMonthlyPayments: Int): Float {
        return if (!interest.equals(0.0F)) {
            (interest * principal).div(1 - (1 + interest).pow(-1 * numMonthlyPayments))
        } else {
            principal.div(numMonthlyPayments)
        }
    }

    fun getNumMonthlyPayments(amortizationPeriodInYears: Int): Int {
        return amortizationPeriodInYears * 12
    }
}