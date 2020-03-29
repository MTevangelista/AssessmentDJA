package br.projeto.assessmentdja.service

import br.projeto.assessmentdja.model.Tarefa
import br.projeto.assessmentdja.model.Tarefas
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TarefaService {

    @GET("infnet.educacao.ws/dadosAtividades.php")
    fun all() : Call<Tarefas>

    @GET("infnet.educacao.ws/dadosAtividades.php/{id}")
    fun show(@Path("id") id : Int) : Call<Tarefa>
}