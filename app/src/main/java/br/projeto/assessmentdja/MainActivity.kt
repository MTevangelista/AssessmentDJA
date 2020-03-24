package br.projeto.assessmentdja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    override fun onClick(view: View) {
        var btnId = view.id

        when(btnId){
            R.id.button_login -> startActivity(Intent(baseContext, LoginActivity::class.java))
            R.id.button_cadastro -> startActivity(Intent(baseContext, button_cadastro::class.java))
        }
    }

    private fun setListeners(){
        button_cadastro.setOnClickListener(this)
        button_login.setOnClickListener(this)
    }
}
