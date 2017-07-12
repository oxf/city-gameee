package cz.visualio.citygame.model

/**
 * Created by stanislav on 7/11/17.
 */
data class Conversation(
        val group: Group,
        val messages: List<Message> = ArrayList<Message>()
)