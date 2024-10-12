package behavioralPatterns

/**
 * 책임 사슬 패턴은 복잡한 로직을 여러 개의 작은 단계로 쪼갠다.
 */
data class Request(val email: String, val question: String)

fun handleRequest(r: Request) {
    // 유효성 검사
    if (r.email.isEmpty() || r.question.isEmpty()) {
        return
    }

    // 인증
//    if (r.isKnownEmail()) {
//        return
//    }

    // 인가
//    if(r.isFromJuniorDeveloper()) {
//        return
//    }

    println("모르겠네요. StackOverFlow는 검색해 보셨나요?")
}
// 다소 지저분하긴 해도 잘 동작한다.
// 어떤 개발자가 2개의 질문을 동시에 보내는 것을 발견했다. 이를 막으려면 함수에 로직을 추가해야 한다.
// 동작을 다른 곳으로 위임하는 더 좋은 방법이 있지 않을까?
interface Handler {
    fun handle(request: Request) : Response
}
data class Response(val answer: String)

// 자바 스타일로 구현하면 로직의 각 부분을 다음과 같이 Handler 안에 넣을 것이다.
class BasicValidationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        if (request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }
        return next.handle(request)
    }
}

// 다른 필터들도 만들고 나면 아래와 같이 원하는 순서대로 조합하면 된다.
//val req = Request("developer@comapny.com", "왜 갑자기 빌드가 안 되죠?")
//val chain = BasicValidationHandler(
//    KnownEmailHandler(
//        JuniorDeveloperHandler(
//            AnswerHandler()
//        )
//    )
//)
//val res = chain.handle(req)

// 코트린스러운 더 나은 방법이 있지 않을끼?

typealias KHandler = (request: Request) -> Response

//val authentication = fun(next: Handler) =
//    fun(request: Request): Response {
//        if (!request.isKnownEmail()) {
//            throw IllegalArgumentException()
//        }
//        return next(request)
//    }
// 여기서 authentication 은 함수를 받아서 새로운 함수를 반환한다. 이 패턴을 사용하면 쉽게 함수들을 조합할 수 있다.

//val req = Request("developer@company.com", "소프트웨어 아키텍트가 왜 필요한가요?")
//val chain = basicValidation(authentication(finalResponse()))
//val res = chain(req)

