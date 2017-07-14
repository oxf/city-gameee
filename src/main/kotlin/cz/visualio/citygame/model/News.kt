package cz.visualio.citygame.model

import cz.visualio.citygame.annotations.NoArg
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
@Document(collection = "news")
@NoArg
data class News(
        @Id var id: Long,
        var title: String, //not empty
        var body: String,
        var imageURL: String?,
        val date: LocalDate = LocalDate.now() //new type
)