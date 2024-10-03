package sofe4640.johnhowe.mortgagecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import sofe4640.johnhowe.mortgagecalculator.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val sharedViewModel : ValuesViewModel by activityViewModels<ValuesViewModel>()
    private var mortgagePaymentAmount = 0.0F

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        calculatePaymentAmountAndUpdate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calculatePaymentAmountAndUpdate() {
        sharedViewModel.mortgagePaymentAmount.observe(viewLifecycleOwner, Observer { amount ->
            amount?.let {
                mortgagePaymentAmount = amount
            } ?: run {
                mortgagePaymentAmount = 0.0F
            }

            val text = getString(R.string.monetary_amount, mortgagePaymentAmount)
            binding.MortgagePaymentView.text = text
        })
        sharedViewModel.principal.observe(viewLifecycleOwner, Observer { amount ->
            amount?.let {
                binding.PrincipalView.text = getString(R.string.monetary_amount, amount)
            } ?: run {
                binding.PrincipalView.text = getString(R.string.null_text)
            }
        })
        sharedViewModel.interest.observe(viewLifecycleOwner, Observer { amount ->
            amount?.let {
                binding.InterestView.text = amount.toString()
            } ?: run {
                binding.InterestView.text = getString(R.string.null_text)
            }
        })
        sharedViewModel.amortizationPeriodInYears.observe(viewLifecycleOwner, Observer { amount ->
            amount?.let {
                binding.AmortizationView.text = amount.toString()
            } ?: run {
                binding.AmortizationView.text = getString(R.string.null_text)
            }
        })

    }
}