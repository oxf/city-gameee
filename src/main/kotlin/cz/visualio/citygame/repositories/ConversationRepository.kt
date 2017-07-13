package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.Conversation
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by stanislav on 7/13/17.
 */
interface ConversationRepository: MongoRepository<Conversation, Long> {

}