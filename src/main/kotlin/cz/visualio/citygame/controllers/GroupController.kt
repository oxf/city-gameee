package cz.visualio.citygame.controllers

import cz.visualio.citygame.model.Conversation
import cz.visualio.citygame.model.Group
import cz.visualio.citygame.model.News
import cz.visualio.citygame.repositories.ConversationRepository
import cz.visualio.citygame.repositories.GroupRepository
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger


/**
 * Created by stanislav on 7/12/17.
 */
@RestController
class GroupController(val repository: GroupRepository, val conversationRepository: ConversationRepository) {

    val logger: Logger = Logger.getLogger("NewsController")

    @GetMapping("/group")
    fun findAll(): MutableIterable<Group>? {
        logger.log(LogRecord(Level.INFO, "Applied GET to ALL groups"))
        return repository.findAll()
    }

    @PostMapping("/group/")
    fun postGroup(@RequestBody group: Group): Group? {
        logger.log(LogRecord(Level.INFO, "Created group with id : "+group.id+"name : "+group.name+" by user id : "+group.ownerId))
        val conv: Conversation = Conversation()
        conversationRepository.save(conv)
        group.conversationId=conv.id
        return repository.save(group)
    }
    @DeleteMapping("/group/{id}")
    fun removeNews(@PathVariable("id") id: Long){
        logger.log(LogRecord(Level.INFO, "Applied DELETE to group with id : "+id))

        return repository.deleteById(id)
    }

}