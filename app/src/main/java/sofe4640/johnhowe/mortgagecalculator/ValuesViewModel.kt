package sofe4640.johnhowe.mortgagecalculator

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ValuesViewModel : ViewModel() {
    var principal = MutableLiveData<Float>()
    var interest = MutableLiveData<Float>()
    var amortizationPeriodInYears = MutableLiveData<Int>()

    //using a mediator means calculations are done on get()
    var mortgagePaymentAmount = MediatorLiveData<Float>().apply {
        addSource(principal) { calculatePaymentAmount() }
        addSource(interest) { calculatePaymentAmount() }
        addSource(amortizationPeriodInYears) { calculatePaymentAmount() }
    }

    //calculate payment amount based on current values and update mortgagePaymentAmount
    private fun calculatePaymentAmount() {
        val p = principal.value ?: 0.0F
        val r = interest.value?.times(0.01F)?.div(12) ?: 0.0F
        val t = amortizationPeriodInYears.value ?: 0
        val calc = Calculator()

        //calculate
        mortgagePaymentAmount.value = calc.getMortgagePaymentAmount(p, r, calc.getNumMonthlyPayments(t))
    }
}
