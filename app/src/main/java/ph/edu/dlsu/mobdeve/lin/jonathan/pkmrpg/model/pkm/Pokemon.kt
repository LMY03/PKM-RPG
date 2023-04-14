package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm

import android.database.sqlite.SQLiteDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.R

class Pokemon(val name: String, val maxHP: Float, val atk: Int, val def: Int, val spd: Int, val drawable: Int) {
    var hp = maxHP

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "PokemonDatabase"
        const val PKM_TABLE = "pokemon_table"

        const val PKM_ID = "id"
        const val PKM_NAME = "name"
        const val PKM_MAXHP = "hp"
        const val PKM_ATK = "atk"
        const val PKM_DEF = "def"
        const val PKM_SPD = "spd"
        const val PKM_IMG = "img"

        val CREATE_PKM_TABLE =
            "CREATE TABLE $PKM_TABLE " +
                    "($PKM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$PKM_NAME TEXT, " +
                    "$PKM_MAXHP INTEGER, " +
                    "$PKM_ATK INTEGER, " +
                    "$PKM_DEF INTEGER, " +
                    "$PKM_SPD INTEGER, " +
                    "$PKM_IMG INTEGER)"

        fun insertData(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_PKM_TABLE)

            db?.execSQL("Insert into $PKM_TABLE " +
                    "($PKM_NAME, $PKM_MAXHP, $PKM_ATK, $PKM_DEF, $PKM_SPD, $PKM_IMG)" +
                    "values ('Charmander', 39, 52, 43, 65," + R.drawable.charmander + ")")
            db?.execSQL("Insert into $PKM_TABLE " +
                    "($PKM_NAME, $PKM_MAXHP, $PKM_ATK, $PKM_DEF, $PKM_SPD, $PKM_IMG)" +
                    "values ('Pidgey', 40, 45, 40, 56," + R.drawable.pidgey + ")")
            db?.execSQL("Insert into $PKM_TABLE " +
                    "($PKM_NAME, $PKM_MAXHP, $PKM_ATK, $PKM_DEF, $PKM_SPD, $PKM_IMG)" +
                    "values ('Rattata', 30, 56, 35, 72," + R.drawable.rattata + ")")
            db?.execSQL("Insert into $PKM_TABLE " +
                    "($PKM_NAME, $PKM_MAXHP, $PKM_ATK, $PKM_DEF, $PKM_SPD, $PKM_IMG)" +
                    "values ('Spearow', 40, 60, 30, 70," + R.drawable.spearow + ")")
            db?.execSQL("Insert into $PKM_TABLE " +
                    "($PKM_NAME, $PKM_MAXHP, $PKM_ATK, $PKM_DEF, $PKM_SPD, $PKM_IMG)" +
                    "values ('Bulbasaur', 45, 45, 49, 45," + R.drawable.bulbasaur + ")")
        }
    }

    fun setFullHp() { this.hp = this.maxHP }
}