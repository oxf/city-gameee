package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.Conversation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/13/17.
 */
@Repository
interface ConversationRepository: MongoRepository<Conversation, Long> {

}