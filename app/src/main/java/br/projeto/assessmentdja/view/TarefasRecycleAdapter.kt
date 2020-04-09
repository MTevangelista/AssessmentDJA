package br.projeto.assessmentdja.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.projeto.assessmentdja.R
import br.projeto.assessmentdja.model.Tarefa
import kotlinx.android.synthetic.main.recycle_layout_tarefa.view.*

class TarefasRecycleAdapter (var tarefas : MutableList<Tarefa>) : RecyclerView.Adapter<TarefasRecycleAdapter.TarefasViewHolder>() {

    class TarefasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descricao = itemView.textView_descricao
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefasViewHolder {
        var view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycle_layout_tarefa, parent, false)
        var tarefasViewHolder = TarefasViewHolder(view)

        return tarefasViewHolder
    }

    override fun getItemCount(): Int = tarefas.size

    override fun onBindViewHolder(holder: TarefasViewHolder, position: Int) {
        holder.descricao.text = tarefas[position].descricao
    }
}