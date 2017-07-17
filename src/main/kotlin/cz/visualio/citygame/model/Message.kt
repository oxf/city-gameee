package cz.visualio.citygame.model

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
data class Message(
        @field: Id var id: Long = 0,
        val senderId: Long,
        var text: String, //not empty
        val date: LocalDateTime = LocalDateTime.now()
)