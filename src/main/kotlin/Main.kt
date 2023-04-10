fun main(args: Array<String>) {
    val r = Requests.get("https://geek-jokes.sameerkumar.website/api?format=json")
    println(r.code)
    println(r.text)
    val r2 = Requests.post("https://postman-echo.com/post", mapOf("num1" to "24", "age" to "100"))
    println(r2.code)
    println(r2.text)
}
