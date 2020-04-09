package br.projeto.assessmentdja.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.projeto.assessmentdja.R
import br.projeto.assessmentdja.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        mViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Eventos
        setListeners()

        // Cria observadores
        observe()
    }

    override fun onClick(view: View) {
        var btnId = view.id

        when (btnId) {
            R.id.button_cadastrar -> {
                var userNome = editText_userNome.text.toString()
                var userEmail = editText_userEmail.text.toString()
                var userSenha = editText_userSenha.text.toString()
                var userConfirmaSenha = editText_userConfirmarSenha.text.toString()
                var userCPF = editText_userCPF.text.toString()

                if (userNome.isNullOrEmpty() or userEmail.isNullOrEmpty() or userSenha.isNullOrEmpty() or userConfirmaSenha.isNullOrEmpty() or userCPF.isNullOrEmpty()) {
                    showToast(getString(R.string.preencha_todos_os_campos))
                } else if (userSenha.equals(userConfirmaSenha)) {
                    mViewModel.save(userNome, userEmail, userSenha, userCPF)
                } else {
                    showToast(getString(R.string.senhas_nao_sao_iguais))
                }
            }
        }
    }

    private fun observe() {
        mViewModel.saveUser.observe(this, Observer {
            if (it) {
                showToast(getString(R.string.usuario_cadastrado_com_suceeso))
                startActivity(Intent(baseContext, LoginActivity::class.java))
            } else {
                showToast(getString(R.string.erroMessage))
            }
            finish()
        })
    }

    private fun setListeners() {
        button_cadastrar.setOnClickListener(this)
    }

    private fun showToast(string: String) {
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}

