package sofe4640.johnhowe.mortgagecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import sofe4640.johnhowe.mortgagecalculator.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val sharedViewModel: ValuesViewModel by activityViewModels<ValuesViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener { //on button click
            setSharedValues()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment) //navigate to calculated value fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setSharedValues() {
        sharedViewModel.principal.value = binding.PrincipalInput.text.toString().toFloat()
        sharedViewModel.interest.value = binding.InterestRateInput.text.toString().toFloat()
        sharedViewModel.amortizationPeriodInYears.value = binding.AmortizationInput.text.toString().toInt()
    }
}