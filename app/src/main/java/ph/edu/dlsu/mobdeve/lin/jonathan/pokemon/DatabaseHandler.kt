package ph.edu.dlsu.mobdeve.lin.jonathan.pokemon

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PokemonDatabase"
        const val PKM_TABLE = "pokemon_table"

        const val PKM_ID = "id"
        const val PKM_NAME = "name"
        const val PKM_MAXHP = "hp"
        const val PKM_ATK = "atk"
        const val PKM_DEF = "def"
        const val PKM_SPD = "spd"
        const val PKM_IMG = "img"
    }

    // Handles creation of the database
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_MEDIA_TABLE =
            "CREATE TABLE $PKM_TABLE " +
                    "($PKM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$PKM_NAME TEXT, " +
                    "$PKM_MAXHP INTEGER, " +
                    "$PKM_ATK INTEGER, " +
                    "$PKM_DEF INTEGER, " +
                    "$PKM_SPD INTEGER, " +
                    "$PKM_IMG INTEGER)"

        db?.execSQL(CREATE_MEDIA_TABLE)

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
                "values ('Bulbasaur', 45, 49, 49, 45," + R.drawable.bulbasaur + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $PKM_TABLE")
        onCreate(db)
    }
}