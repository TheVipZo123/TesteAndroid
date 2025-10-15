package com.example.testeandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testeandroid.data.Task
import android.widget.CheckBox


class TaskAdapter(
    private val onCheck: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.VH>(DIFF) {

    companion object {
        val DIFF = object: DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val t = getItem(position)
        holder.bind(t)
    }

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        private val txt = v.findViewById<TextView>(R.id.tv_desc)
        private val chkDone = v.findViewById<CheckBox>(R.id.chk_done)
        private val btnDelete = v.findViewById<ImageButton>(R.id.btn_delete)

        fun bind(task: Task) {
            txt.text = task.description

            // Set checkbox state
            chkDone.isChecked = task.done

            // Handle checkbox click
            chkDone.setOnCheckedChangeListener { _, isChecked ->
                task.done = isChecked
                onCheck(task)
            }

            btnDelete.setOnClickListener { onDelete(task) }
        }
    }
}
