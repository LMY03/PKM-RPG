package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model

import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.R
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon

class PokemonLoader {
    companion object {
        fun generateMyPKM() : Pokemon {
            return Pokemon("Charmander", 39f, 52, 43, 65, R.drawable.charmander)
        }
        fun generatePidgey() : Pokemon {
            return Pokemon("Pidgey", 40f, 45, 40, 56, R.drawable.pidgey)
        }
        fun generateRattata() : Pokemon {
            return Pokemon("Rattata", 30f, 56, 35, 72, R.drawable.rattata)
        }
        fun generateSpearow() : Pokemon {
            return Pokemon("Spearow", 40f, 60, 30, 70, R.drawable.spearow)
        }
        fun generateBulbasaur() : Pokemon {
            return Pokemon("Bulbasaur", 45f, 49, 49, 45, R.drawable.bulbasaur)
        }
    }
}