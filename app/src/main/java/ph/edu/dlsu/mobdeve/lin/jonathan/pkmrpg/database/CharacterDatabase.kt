package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.Character

class CharacterDatabase(context: Context) {

    private var databaseHandler : DatabaseHandler

    init {
        this.databaseHandler = DatabaseHandler(context, Character.DATABASE_NAME, Character.DATABASE_VERSION)
    }

    fun addCharacter(char: Character) : Int {
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(Character.CHAR_NAME, char.name)
        contentValues.put(Character.CHAR_X, char.x)
        contentValues.put(Character.CHAR_Y, char.y)

        val id = db.insert(Character.CHAR_TABLE, null, contentValues)

        db.close()

        return id.toInt()
    }

    fun updateChar(char: Character) {
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(Character.CHAR_NAME, char.name)
        contentValues.put(Character.CHAR_X, char.x)
        contentValues.put(Character.CHAR_Y, char.y)

        db.update(Character.CHAR_TABLE, contentValues, "${Character.CHAR_ID} = ${char.id}", null)
        db.close()
    }

    @SuppressLint("Range")
    fun getCharList(): ArrayList<Character> {
        val result = ArrayList<Character>()

        val db = databaseHandler.readableDatabase
        val cursor: Cursor?

        try{
            cursor = db.rawQuery("SELECT * FROM ${Character.CHAR_TABLE}", null)
        } catch (e: SQLiteException) {
            db.close()
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val char = Character(cursor.getString(1))
                char.id = cursor.getString(0)
                char.x = cursor.getInt(2)
                char.y = cursor.getInt(3)
                result.add(char)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return result
    }
}