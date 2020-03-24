package br.projeto.assessmentdja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.projeto.assessmentdja.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDb: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setListeners()
    }

    override fun onClick(view: View) {
        var btnId = view.id

        when(btnId){
            R.id.button_cadastrar -> {
                var userNome = editText_userNome.text.toString()
                var userEmail = editText_userEmail.text.toString()
                var userSenha = editText_userSenha.text.toString()
                var userConfirmaSenha = editText_userConfirmarSenha.text.toString()
                var userCPF = editText_userCPF.text.toString()

                if (userNome.isNullOrEmpty() or userEmail.isNullOrEmpty() or userSenha.isNullOrEmpty() or userConfirmaSenha.isNullOrEmpty() or userCPF.isNullOrEmpty()) {
                    showToast("Preencha todos os campos")
                } else if (userSenha.equals(userConfirmaSenha)){
                    createUser(userNome, userEmail, userSenha, userCPF)
                } else {
                    showToast("Erro! Senhas não são iguais.")
                }
            }
        }
    }

    private fun createUser(userNome: String, userEmail: String, userSenha: String, userCPF: String){
        mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(userEmail, userSenha)
            .addOnSuccessListener {
                mDb = FirebaseFirestore.getInstance()
                mDb.collection("users")
                    .document(mAuth.currentUser!!.uid)
                    .set(User(userNome, userEmail, userSenha, userCPF))
                    .addOnSuccessListener {
                        showToast("O seu cadastro foi criado com sucesso.")
                        startActivity(Intent(baseContext, LoginActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener{
                        showToast("Erro ao efetuar o cadastro!")
                    }
            }
            .addOnFailureListener {
                showToast("{${it.message}}")
            }
    }

    private fun setListeners(){
        button_cadastrar.setOnClickListener(this)
    }

    private fun showToast(string: String){
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}
