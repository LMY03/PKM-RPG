package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg

import okhttp3.*
import org.json.JSONObject
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class API {
    companion object {

        fun getRandomName(): String? {
            val url = "https://randomuser.me/api/"
            val client = OkHttpClient()

            val request = Request.Builder()
                .url(url)
                .build()

            val queue: BlockingQueue<String?> = LinkedBlockingQueue()

            val thread = Thread {
                try {
                    val response = client.newCall(request).execute()
                    val body = response.body?.string()
                    val name = if (response.isSuccessful && !body.isNullOrEmpty()) {
                        val json = JSONObject(body)
                        json.getJSONArray("results").getJSONObject(0)
                            .getJSONObject("name").getString("first").capitalize()
                    } else {
                        null
                    }
                    queue.put(name)
                } catch (e: Exception) {
                    queue.put("boy")
                }
            }

            thread.start()

            return queue.take()
        }
    }
}
