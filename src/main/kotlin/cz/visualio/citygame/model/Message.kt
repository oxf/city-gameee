package cz.visualio.citygame.model

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 * Domain model for Message in chats
 */
data class Message(
        @field: Id var id: Long = 0,
        val senderId: Long,
        val convId: Long,
        var text: String, //not empty
        val date: LocalDateTime = LocalDateTime.now()
)