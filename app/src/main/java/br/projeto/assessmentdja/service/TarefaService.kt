package br.projeto.assessmentdja.service

import br.projeto.assessmentdja.model.Tarefas
import retrofit2.Call
import retrofit2.http.GET

interface TarefaService {

    @GET("/dadosAtividades.php")
    fun all() : Call<Tarefas>
}