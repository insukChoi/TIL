import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(
        CIO,
        port = 8080,
        module = Application::mainModule
    ).start(wait = true)

    println("다음 주소로 접속하세요: http://localhost:8080")
}

fun Application.mainModule() {
    DB.connect()

    transaction {
        SchemaUtils.create(CatsTable)
    }

    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        get("/status") {
            call.respond(mapOf("status" to "OK"))
        }
        cats()
    }
}

fun Route.cats() {
    route("/cats") {
        post {
            val parameters = call.receiveParameters()
            val name = requireNotNull(parameters["name"])
            val age = parameters["age"]?.toInt() ?: 0
            transaction {
                CatsTable.insert { cat ->
                    cat[CatsTable.name] = name
                    cat[CatsTable.age] = age
                }
            }
            call.respond(HttpStatusCode.Created)
        }
        get {
            val cats = transaction {
                CatsTable.selectAll().map { row ->
                    Cat(
                        row[CatsTable.id].value,
                        row[CatsTable.name],
                        row[CatsTable.age],
                    )
                }
            }

            call.respond(cats)
        }
        get("/{id}") {
            val id = requireNotNull(call.parameters["id"]).toInt()
            val cat = transaction {
                CatsTable.select {
                    CatsTable.id eq id
                }.firstOrNull()
            }

            if (cat == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(
                    Cat(
                        cat[CatsTable.id].value,
                        cat[CatsTable.name],
                        cat[CatsTable.age],
                    )
                )
            }
        }
    }
}