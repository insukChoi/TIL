import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ServerTest {
    @BeforeEach
    fun setUp() {
        DB.connect()
        transaction {
            SchemaUtils.drop(CatsTable)
        }
    }

    @Test
    fun testStatus() {
        withTestApplication(Application::mainModule) {
            val response = handleRequest(HttpMethod.Get, "/status").response
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("""{"status":"OK"}""", response.content)
        }
    }

    @Test
    fun `POST 요청은 새로운 고양이를 생성한다`() {
        withTestApplication(Application::mainModule) {
            val response = handleRequest(HttpMethod.Post, "/cats") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
                setBody(
                    listOf(
                        "name" to "두부",
                        "age" to 4.toString()
                    ).formUrlEncode()
                )
            }.response
            assertEquals(HttpStatusCode.Created, response.status())
        }
    }

    @Test
    fun `ID를 지정한 GET 요청은 특정한 고양이 정보를 조회한다`() {
        withTestApplication(Application::mainModule) {
            val id = transaction {
                CatsTable.insertAndGetId { cat ->
                    cat[name] = "복실이"
                }
            }
            val response = handleRequest(HttpMethod.Get, "/cats/$id").response
            assertEquals("""{"id":1,"name":"복실이","age":0}""", response.content)
        }
    }
}