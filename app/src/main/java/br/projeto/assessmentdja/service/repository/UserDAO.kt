package br.projeto.assessmentdja.service.repository

interface UserDAO {

    fun save(user: UserDAO): Long
}