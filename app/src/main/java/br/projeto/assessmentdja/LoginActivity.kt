package br.projeto.assessmentdja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListeners()
    }

    override fun onClick(view: View) {
        var btnId = view.id

        when (btnId){
            R.id.button_Logar -> {
                var email = editText_email.text.toString()
                var senha = editText_senha.text.toString()

                if (email.isNullOrEmpty() or senha.isNullOrEmpty()){
                    showToast("Por favor, preencha todos os campos!")
                } else {
                    mAuth = FirebaseAuth.getInstance()
                    mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnSuccessListener {
                            startActivity(Intent(baseContext, PrincipalActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            showToast("Falha na autenticação: ${it.message}.")
                        }
                }
            }
        }
    }

    private fun setListeners(){
        button_Logar.setOnClickListener(this)
    }

    private fun showToast(string: String){
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}
