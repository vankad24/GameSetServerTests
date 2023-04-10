import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.stream.Collectors

class Requests {
    companion object {
        fun get(link: String, params: Map<String, String> = HashMap()): Response {
            var reqParams = ""
            if (params.isNotEmpty())reqParams="?"+params.map { URLEncoder.encode(it.key, "UTF-8") + "=" + URLEncoder.encode(it.value, "UTF-8") }
                .joinToString(separator = "&")
            with(URL(link+reqParams).openConnection() as HttpURLConnection) {
                requestMethod = "GET"

                inputStream.bufferedReader().use {
                    return Response(responseCode,it.lines().collect(Collectors.joining("\n")))
                }
            }
        }

        fun post(link: String, data: Map<String, String> = HashMap(), params: Map<String, String> = HashMap()): Response {
            val reqData = data.map { URLEncoder.encode(it.key, "UTF-8") + "=" + URLEncoder.encode(it.value, "UTF-8") }
                .joinToString(separator = "&")
            var reqParams = ""
            if (params.isNotEmpty())reqParams="?"+params.map { URLEncoder.encode(it.key, "UTF-8") + "=" + URLEncoder.encode(it.value, "UTF-8") }
                .joinToString(separator = "&")
            with(URL(link+reqParams).openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                doOutput = true
                val wr = OutputStreamWriter(outputStream);
                wr.write(reqData);
                wr.flush();

                inputStream.bufferedReader().use {
                    return Response(responseCode,it.lines().collect(Collectors.joining("\n")))
                }
            }
        }

    }
}