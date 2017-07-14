package cz.visualio.citygame.controllers

import cz.visualio.citygame.model.*
import cz.visualio.citygame.repositories.ConversationRepository
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger


/**
 * Created by stanislav on 7/12/17.
 */
@RestController
class GroupController(val repository: GroupRepository, val conversationRepository: ConversationRepository, val userRepository: UserRepository, val inviteRepository: InviteRepository) {

    val logger: Logger = Logger.getLogger("NewsController")

    @GetMapping("/group")
    fun findAll(): MutableIterable<Group>? {
        logger.log(LogRecord(Level.INFO, "Applied GET to ALL groups"))
        return repository.findAll()
    }

    @PostMapping("/group/")
    fun postGroup(@RequestBody group: Group): Group? {
        logger.log(LogRecord(Level.INFO, "Created new GROUP : "+group.toString()))
        val conv: Conversation = Conversation()
        conversationRepository.save(conv)
        group.conversationId=conv.id
        //invites
        for(i in group.invites) {
            inviteRepository.save(Invite(subjId = group.owner.userId, objId = i, groupId = group.id))
        }
        return repository.save(group)
    }
    @DeleteMapping("/group/{id}")
    fun removeGroup(@PathVariable("id") id: Long){
        logger.log(LogRecord(Level.INFO, "DELETEed GROUP with id : "+id))
        return repository.deleteById(id)
    }
    @PutMapping("/group/{id}")
    fun editGroup(
            @PathVariable("id") id: Long,
            @RequestBody group: Group
    ) {
        group.invites
                .filterNot { group.invites.contains(it) }
                .forEach { inviteRepository.save(Invite(subjId = group.owner.userId, objId = it, groupId = group.id)) }
        repository.save(group)
    }

/*    @GetMapping("/group/{id}/ownedBy/")
    fun getOwnedGroups(@PathVariable("id") id: Long) {
        repository.find
    }
    @GetMapping("/group/{id}/memberOfGroups")
    fun getMemberGroup(@PathVariable("id") id: Long) {

    }*/
}