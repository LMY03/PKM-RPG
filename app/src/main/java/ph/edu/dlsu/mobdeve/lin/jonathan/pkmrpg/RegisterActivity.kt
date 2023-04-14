package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.CharacterDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.UserDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.databinding.ActivityRegisterBinding
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.User
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.Character

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerSubmit.setOnClickListener {
            val charnameInput = binding.registerCharacterName.text
            val usernameInput = binding.registerUsername.text
            val passwordInput = binding.registerPassword.text

            if (charnameInput != null && usernameInput != null && passwordInput != null) {
                val user = User(usernameInput.toString(), passwordInput.toString())
                user.userId = UserDatabase(applicationContext).addMedia(user).toString()

                val char = Character(charnameInput.toString())
                char.id = CharacterDatabase(applicationContext).addCharacter(char).toString()
                user.character = char

                var intent = Intent(this, GameActivity::class.java)
                intent.putExtra("id", user.userId)
                startActivity(intent)
            }
        }
    }
}