package cz.visualio.citygame.model

import cz.visualio.citygame.annotations.NoArg
import cz.visualio.citygame.utlis.IdIncrementer
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 * Domain model for news in feed
 */
val idIncNews: IdIncrementer = IdIncrementer()
@Document(collection = "news")
@NoArg
data class News(
        @Id var id: Long = idIncNews.getNextId(),
        var title: String, //not empty
        var body: String = "",
        var imageURL: String?,
        val date: LocalDateTime = LocalDateTime.now() //new type
)