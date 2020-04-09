package br.projeto.assessmentdja.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.projeto.assessmentdja.model.UserModel
import br.projeto.assessmentdja.service.repository.UserRepository
import br.projeto.assessmentdja.view.PrincipalActivity
import com.google.firebase.auth.FirebaseAuth


class UserViewModel (application: Application) : AndroidViewModel(application) {

    // Contexto e acesso a dados
    private val mContext = application.applicationContext
    private val mUserRepository: UserRepository = UserRepository(mContext)

    private var mSaveUser = MutableLiveData<Boolean>()
    val saveUser: LiveData<Boolean> = mSaveUser

    private var mUser = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = mUser

    /**
     * Salva convidado
     * */
    fun save(userNome: String, userEmail: String, userSenha: String, userCPF: String) {
        val user = UserModel(userNome, userEmail, userSenha, userCPF)
        mSaveUser.value = mUserRepository.save(userNome, userEmail, userSenha, userCPF)
    }

    /**
     * efetuar login
     * */
    fun login(userEmail: String, userSenha: String){
        mSaveUser.value = mUserRepository.login(userEmail, userSenha)
    }
}