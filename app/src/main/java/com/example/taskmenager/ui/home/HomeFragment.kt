package com.example.taskmenager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskmenager.R
import com.example.taskmenager.databinding.FragmentHomeBinding
import com.example.taskmenager.model.Task
import com.example.taskmenager.ui.App
import com.example.taskmenager.ui.home.adapter.TaskAdapter
import com.example.taskmenager.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onLongClick, this::onClick)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter
        setData()
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onLongClick(task: Task) {
        val alert = AlertDialog.Builder(requireContext())
            .setTitle("Вы хотите удалить запись?")
            .setPositiveButton("Да") { _, _ ->
                App.db.taskDao().delete(task)
                setData()
            }.setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
        alert.create().show()
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK_FOR_EDIT to task))
    }

    private fun setData() {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TASK_FOR_EDIT = "task.for.edit"
    }
}