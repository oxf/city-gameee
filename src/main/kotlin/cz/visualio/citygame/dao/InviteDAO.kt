package cz.visualio.citygame.dao

import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.model.Member
import cz.visualio.citygame.repositories.InviteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.logging.Level
import java.util.logging.LogRecord

/**
 * Created by stanislav on 7/14/17.
 */
@Repository
class InviteDAO {
    @Autowired
    private lateinit var inviteRepository: InviteRepository
    @Autowired
    private lateinit var groupDAO: GroupDAO
    @Autowired
    private lateinit var userDAO: UserDAO

    fun  findByObjId(id: Long): MutableIterable<Invite>? {
        return inviteRepository.findByObjId(id)
    }

    fun  save(invite: Invite): Invite? {
        //verify properties
        if(inviteRepository.findOne(invite.id)!=null) {
            throw Exception("Invite with such id = "+invite.id+"already exists")
        }
        if(groupDAO.findOne(invite.groupId)?.owner?.userId!=invite.subjId) {
            throw Exception("Only owner can invite users to group")
        }
        userDAO.findOne(invite.objId)
            ?: throw Exception("User with id = "+invite.objId+" does not exist")
        return inviteRepository.save(invite)
    }

    fun  edit(invite: Invite) : Invite? {
        if(invite.status==true) {
            //add user to group
            val group = groupDAO.findOne(invite.groupId)
            group?.addMember(Member(invite.objId))
            logger.log(LogRecord(Level.INFO, "USER with id : "+invite.objId+" was added to GROUP id : "+invite.groupId))
            group.invites.remove(invite.objId)
            groupRepository.save(group)
            repository.delete(invite.id)
            return repository.delete(invite)
        } else if(invite.status==false) {
            return repository.delete(invite)
        }
        return inviteRepository.save(invite)
    }

    fun  findByGroupId(id: Long): MutableIterable<Invite>? {
        return inviteRepository.findByGroupId(id)
    }
}