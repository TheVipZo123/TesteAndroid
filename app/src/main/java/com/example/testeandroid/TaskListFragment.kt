package com.example.testeandroid

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeandroid.LoginFragment
import com.example.testeandroid.data.Task
import com.example.testeandroid.databinding.FragmentTaskListBinding
import com.example.testeandroid.ui.theme.TaskViewModel
import com.example.testeandroid.ui.theme.LoginViewModel

class TaskListFragment : Fragment() {
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private val vm: TaskViewModel by activityViewModels()
    private val loginVm: LoginViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = TaskAdapter(
            onCheck = { task -> vm.toggleDone(task) },
            onDelete = { task -> confirmDelete(task) }
        )
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        vm.tasks.observe(viewLifecycleOwner, Observer { list ->
            adapter.submitList(list)
        })

        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }

        binding.btnLogout.setOnClickListener {
            // Reset login state first
            loginVm.resetLoginState()

            // Navigate to login and clear back stack
            (activity as? MainActivity)?.navigateTo(LoginFragment(), addToBackStack = false, clearBackStack = true)
        }
    }

    private fun confirmDelete(task: Task) {
        AlertDialog.Builder(requireContext())
            .setTitle("Excluir tarefa")
            .setMessage("Deseja realmente excluir esta tarefa?")
            .setPositiveButton("Sim") { _, _ -> vm.deleteTask(task) }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun showAddDialog() {
        val et = EditText(requireContext())
        et.hint = "Descrição"
        AlertDialog.Builder(requireContext())
            .setTitle("Nova tarefa")
            .setView(et)
            .setPositiveButton("Adicionar") { _, _ ->
                val desc = et.text.toString()
                if (desc.isBlank()) {
                    Toast.makeText(requireContext(), "Descrição vazia", Toast.LENGTH_SHORT).show()
                } else {
                    vm.addTask(desc)
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
