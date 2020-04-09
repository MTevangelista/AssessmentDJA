package br.projeto.assessmentdja.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.projeto.assessmentdja.R
import br.projeto.assessmentdja.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]

        // Eventos
        setListeners()

        // Cria observadores
        observe()

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
                    mViewModel.login(email, senha)
                }
            }
        }
    }

    private fun observe() {
        mViewModel.saveUser.observe(this, Observer {
            if (it) {
               showToast("Sucesso")
                startActivity(Intent(baseContext, PrincipalActivity::class.java))
            } else {
                showToast("Falha")
            }
            finish()
        })
    }

    private fun setListeners(){
        button_Logar.setOnClickListener(this)
    }

    private fun showToast(string: String){
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}
