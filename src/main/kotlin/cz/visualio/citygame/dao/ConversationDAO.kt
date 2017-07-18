package cz.visualio.citygame.dao

import cz.visualio.citygame.model.Conversation
import cz.visualio.citygame.repositories.ConversationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/14/17.
 */
@Repository
class ConversationDAO {
    @Autowired
    private lateinit var conversationRepository: ConversationRepository

    fun  save(conv: Conversation): Conversation? {
        return conversationRepository.save(conv)
    }
}