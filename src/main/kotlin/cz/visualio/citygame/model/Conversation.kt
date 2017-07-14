package cz.visualio.citygame.model

import cz.visualio.citygame.utlis.IdIncrementer
import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue

/**
 * Created by stanislav on 7/11/17.
 */
val idIncConv: IdIncrementer = IdIncrementer()
data class Conversation(
        @Id val id: Long = idIncConv.getNextId(),
        val messages_ids: List<Long> = ArrayList<Long>()
)