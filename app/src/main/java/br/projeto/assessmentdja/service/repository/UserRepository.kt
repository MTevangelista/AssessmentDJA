package br.projeto.assessmentdja.service.repository

import android.content.Context
import android.content.Intent
import br.projeto.assessmentdja.model.UserModel
import br.projeto.assessmentdja.service.constants.DatabaseConstants
import br.projeto.assessmentdja.view.PrincipalActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository(context: Context) {

    // Acesso ao banco de dados
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDb: FirebaseFirestore

    /**
     * Salvar usuário
     */
    fun save(userNome: String, userEmail: String, userSenha: String, userCPF: String): Boolean {
        return try {
            mAuth = FirebaseAuth.getInstance()
            mAuth.createUserWithEmailAndPassword(userEmail, userSenha)
                    .addOnSuccessListener {
                        mDb = FirebaseFirestore.getInstance()
                        mDb.collection(DatabaseConstants.COLLECTION.USERS)
                                .document(mAuth.currentUser!!.uid)
                                .set(UserModel(userNome, userEmail, userSenha, userCPF))
                                .addOnSuccessListener {
                                    mAuth.signOut()
                                }
                    }
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * efetuar login
     */
    fun login(userEmail: String, userSenha: String): Boolean {
        return try {
            mAuth = FirebaseAuth.getInstance()
            mAuth.signInWithEmailAndPassword(userEmail, userSenha)
            true
        } catch (e: Exception) {
            false
        }
    }

}