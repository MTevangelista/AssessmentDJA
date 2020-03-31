package br.projeto.assessmentdja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.projeto.assessmentdja.client.RetrofitClient
import br.projeto.assessmentdja.model.Tarefas
import br.projeto.assessmentdja.service.TarefaService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrincipalActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

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
                    var tarefas = response.body()?.tarefa

                    if (tarefas != null) {
                        recycle_tarefas.adapter = TarefasRecycleAdapter(tarefas.toMutableList())
                        recycle_tarefas.layoutManager = LinearLayoutManager(baseContext)
                    } else {
                         showToast("Lista está vazia")
                    }
                }
            })

        floatingActionButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
    }

    private fun showToast(string: String){
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}
