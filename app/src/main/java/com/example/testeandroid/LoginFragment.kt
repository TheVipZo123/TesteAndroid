package com.example.testeandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.testeandroid.databinding.FragmentLoginBinding
import com.example.testeandroid.ui.theme.LoginState
import com.example.testeandroid.ui.theme.LoginViewModel
import com.example.testeandroid.TaskListFragment
import kotlinx.coroutines.flow.collect

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val vm: LoginViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            vm.login(binding.etUser.text.toString(), binding.etPassword.text.toString())

        }

        lifecycleScope.launchWhenStarted {

            vm.loginState.collect { state ->
                binding.progress.visibility = View.GONE
                when(state){
                    is LoginState.Idle -> {}
                    is LoginState.Loading -> { binding.progress.visibility = View.VISIBLE }
                    is LoginState.Success -> {
                        // navigate to list
                        (activity as? MainActivity)?.navigateTo(TaskListFragment(), false)
                    }
                    is LoginState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
