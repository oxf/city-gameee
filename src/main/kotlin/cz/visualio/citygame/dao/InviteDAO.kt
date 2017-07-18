package cz.visualio.citygame.dao

import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.model.Member
import cz.visualio.citygame.model.User
import cz.visualio.citygame.repositories.InviteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

/**
 * Created by stanislav on 7/14/17.
 * Data access class to control ccess to repository, verifying values,
 */
@Repository
class InviteDAO {
    @Autowired
    private lateinit var inviteRepository: InviteRepository
    @Autowired
    private lateinit var groupInviteDAO: GroupInviteDAOService
    @Autowired
    private lateinit var userDAO: UserDAO

    val logger: Logger = Logger.getLogger("InviteDAO")

    fun  findByObjId (id: Long): MutableIterable<Invite>? {
        return inviteRepository.findByObjId(id)
    }

    fun save(invite: Invite): Invite? {
        //verify properties
        if(inviteRepository.findOne(invite.id)!=null) {
            throw Exception("Invite with such id = "+invite.id+"already exists")
        }
        if(groupInviteDAO.findOne(invite.groupId)?.owner?.userId!=invite.subjId) {
            throw Exception("Only owner can invite users to group")
        }
        userDAO.findOne(invite.objId)
            ?: throw Exception("User with id = "+invite.objId+" does not exist")
        return inviteRepository.save(invite)
    }

    fun edit(invite: Invite, id: Long) : Invite? {
        var oldInvite: Invite? = inviteRepository.findOne(id)
        oldInvite ?: throw Exception("Invite with such id does not exist")
        if((oldInvite.subjId!=invite.subjId)||(oldInvite.objId!=invite.objId)||(oldInvite.groupId!=invite.groupId)) {
            throw Exception("You cant change invite params")
        }
        if(invite.status==true) {
            //add user to group
            val group = groupInviteDAO.findOne(invite.groupId) //find group by id in invite
            if(group != null) {
                group?.addMember(Member(invite.objId)) //add user reference to group
                logger.log(LogRecord(Level.INFO, "USER with id : " + invite.objId + " was added to GROUP id : " + invite.groupId))
                group.invites.remove(invite.objId) //remove invite from list in group
                groupInviteDAO.save(group) //save group
                inviteRepository.delete(invite.id) //remove invite from db
                val newMember: User = userDAO.findOne(invite.objId) ?: throw Exception("User with such id not found")
                newMember.addMemberedGroup(group.id) //add
                userDAO.edit(newMember)
                return null
            }
        } else if(invite.status==false) {
            inviteRepository.delete(invite.id)
            return null
        }
        return inviteRepository.save(invite)
    }

    fun  findByGroupId(id: Long): MutableIterable<Invite>? {
        return inviteRepository.findByGroupId(id)
    }
}