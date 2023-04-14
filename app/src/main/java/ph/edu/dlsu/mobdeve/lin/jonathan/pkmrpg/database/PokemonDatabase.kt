package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon

class PokemonDatabase (context: Context) {

    private var databaseHandler : DatabaseHandler

    init {
        this.databaseHandler = DatabaseHandler(context, Pokemon.DATABASE_NAME, Pokemon.DATABASE_VERSION)
    }

    @SuppressLint("Range")
    fun getPKMList(): ArrayList<Pokemon>{
        val result = ArrayList<Pokemon>()

        val db = databaseHandler.readableDatabase
        val cursor: Cursor?

        try{
            cursor = db.rawQuery("SELECT * FROM ${Pokemon.PKM_TABLE}", null)
        } catch (e: SQLiteException) {
            db.close()
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val pkm = Pokemon (
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
                )
                result.add(pkm)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return result
    }
}