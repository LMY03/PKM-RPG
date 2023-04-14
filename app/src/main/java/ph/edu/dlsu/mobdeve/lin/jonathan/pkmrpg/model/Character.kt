package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model

import android.database.sqlite.SQLiteDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.CharacterDatabase

class Character(name : String) {
    lateinit var id : String
    var name = name
    val pokemon = PokemonLoader.generateMyPKM()
    var x : Int = 1
    var y : Int = 3

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "CharacterDatabase"
        const val CHAR_TABLE = "character_table"

        const val CHAR_ID = "id"
        const val CHAR_NAME = "name"
        const val CHAR_X = "x"
        const val CHAR_Y = "y"

        val CREATE_CHAR_TABLE =
            "CREATE TABLE $CHAR_TABLE " +
                    "($CHAR_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$CHAR_NAME TEXT, " +
                    "$CHAR_X INTEGER, " +
                    "$CHAR_Y INTEGER)"

        fun insertData(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_CHAR_TABLE)

            db?.execSQL(
                "Insert into $CHAR_TABLE " +
                        "($CHAR_NAME, $CHAR_X, $CHAR_Y)" +
                        "values ('Jin', 1, 3)"
            )
        }
    }

    fun move(x : Int, y : Int) {
        this.x += x
        this.y += y
    }
}