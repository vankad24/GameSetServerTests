import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
//Tutorial:
//https://kotlinlang.org/docs/jvm-test-using-junit.html
class ExampleTest {

    @Test
    fun sum() {
        val example = Example()
        assertEquals(24,example.sum(17,7))
    }
}