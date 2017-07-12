package cz.visualio.citygame.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
@Document(collection = "user")
data class User(
        @Id var id: Long = 0,
        var username: String = "",
        var mail: String = "",
        var password : String = ""
)