package `1_creationalPatterns`

class Mail @PublishedApi internal constructor() {
    companion object {
        inline operator fun invoke(block: Set.() -> Unit): Get {
            val mail = Mail()
            Set(mail).block()
            return Get(mail)
        }
    }
    var to: ArrayList<String>? = null
    var title: String = ""
    var content: String = ""
    var file: ArrayList<String>? = null
}

@JvmInline value class Set(@PublishedApi internal val mail: Mail) {
    fun to(to: String, vararg others: String) = (mail.to ?: arrayListOf<String>().also { mail.to = it }).run {
        add(to)
        addAll(others)
    }
    fun title(title: String) {
        require(title.isNotBlank()) { "Title cannot be blank." }
        mail.title = title
    }
    fun content(content: String) {
        require(content.isNotBlank()) { "Content cannot be blank." }
        mail.content = content
    }
    fun file(file: String, vararg others: String) {
        (mail.file ?: arrayListOf<String>().also { mail.file = it }).run {
            add(file)
            addAll(others)
        }
    }
    fun s3(bucket: String, key: String) = file("s3://$bucket/$key")
    fun googleDrive(fileId: String) = file("googledrive:/$fileId")
}

@JvmInline value class Get(@PublishedApi internal val mail: Mail) {
    inline val to: List<String> get() = mail.to ?: listOf()
    inline val title: String get() = mail.title
    inline val content: String get() = mail.content
    inline val file: List<String> get() = mail.file ?: listOf()
}

fun main() {
    val mail = Mail {
        to("insuk@gmail.com")
        title("hello")
        content("world")
        s3("bucket", "key")
    }
}