package cz.visualio.citygame.controllers

import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.model.Member
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

/**
 * Created by stanislav on 7/13/17.
 */

@RestController
class InviteController(val repository: InviteRepository, val groupRepository: GroupRepository, val userRepository: UserRepository) {
    val logger: Logger = Logger.getLogger("InviteController")

    @GetMapping("/invite/{id}")
    fun getInvitesForUser(@PathVariable id : Long): MutableIterable<Invite>? {
        logger.log(LogRecord(Level.INFO, "applied GET to all invites for user with id = "+id))
        return repository.findByObjId(id)
    }

    @PutMapping("/invite/{id}")
    fun editInvite(
            @PathVariable id : Long,
            @RequestBody invite: Invite
    ) {
        assert(invite.objId == id)
        if(invite.status==true) {
            //add user to group
            val group = groupRepository.findOne(invite.groupId)
            group.addMember(Member(invite.objId))
            logger.log(LogRecord(Level.INFO, "USER with id : "+invite.objId+" was added to GROUP id : "+invite.groupId))
            group.invites.remove(invite.objId)
            groupRepository.save(group)
            repository.delete(invite.id)
            return repository.delete(invite)
        } else if(invite.status==false) {
            return repository.delete(invite)
        }
        repository.save(invite)
        return Unit
    }
}