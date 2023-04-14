package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.adapter.Adapter
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.DatabaseHandler
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.PokemonDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.UserDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.databinding.ActivityMainBinding
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val usernameInput = binding.usernametext.text
            val passwordInput = binding.passwordtext.text
            if (usernameInput != null && passwordInput != null) {
                UserDatabase(this).getUserList().forEach {
                    if (usernameInput.toString() == it.username)
                        if (passwordInput.toString() == it.password) {
                            var intent = Intent(this, GameActivity::class.java)
                            intent.putExtra("id", it.userId)
                            startActivity(intent)
                        }
                }
            }
        }

        binding.registerButton.setOnClickListener {
            var goToRegister = Intent(this, RegisterActivity::class.java)
            startActivity(goToRegister)
        }
    }
}