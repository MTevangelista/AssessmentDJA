package br.projeto.assessmentdja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.projeto.assessmentdja.client.RetrofitClient
import br.projeto.assessmentdja.model.Tarefas
import br.projeto.assessmentdja.service.TarefaService
import kotlinx.android.synthetic.main.activity_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        RetrofitClient
            .getInstance()
            .create(TarefaService::class.java)
            .all().enqueue(object : Callback<Tarefas>{

                override fun onFailure(call: Call<Tarefas>, t: Throwable) {
                    showToast("Erro: ${t.message}")
                }

                override fun onResponse(call: Call<Tarefas>, response: Response<Tarefas>) {
                    showToast("Lista criada")
                    var tarefas = response.body()?.tarefa

                    if (tarefas != null) {
                        recycle_tarefas.adapter = TarefasRecycleAdapter(tarefas.toMutableList())
                        recycle_tarefas.layoutManager = LinearLayoutManager(baseContext)
                    } else {
                         showToast("Lista está vazia")
                    }
                }
            })

//        var livroService = RetrofitClient
//            .getInstance()?.create(TarefaService::class.java)
//
//        livroService?.all()?.enqueue(object : Callback<List<Tarefa>>{
//            override fun onFailure(call: Call<List<Tarefa>>, t: Throwable) {
//                showToast("${t.message}")
//            }
//
//            override fun onResponse(call: Call<List<Tarefa>>, response: Response<List<Tarefa>>) {
//                //pgrBarHomeFragment.visibility = View.INVISIBLE
//                var tarefas = mutableListOf<Tarefa>()
//                response.body()?.forEach {
//                    tarefas.add(it)
//                }
//                listView_tarefas.adapter = ArrayAdapter(
//                    baseContext!!.applicationContext, android.R.layout.simple_list_item_1, tarefas)
//            }
//
//        })

    }

    private fun showToast(string: String){
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}
