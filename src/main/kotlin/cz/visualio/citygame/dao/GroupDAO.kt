package cz.visualio.citygame.dao

import cz.visualio.citygame.model.Conversation
import cz.visualio.citygame.model.Group
import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.repositories.ConversationRepository
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/14/17.
 */
@Repository
class GroupDAO {
    @Autowired
    private lateinit var groupRepository: GroupRepository
    @Autowired
    private lateinit var conversationDAO: ConversationDAO
    @Autowired
    private lateinit var groupInviteDAO: GroupInviteDAOService
    @Autowired
    private lateinit var userDAO: UserDAO

    fun findAll(): MutableIterable<Group>? {
        return groupRepository.findAll()
    }

    fun save(group: Group): Group? {
        //verify properties
        if (groupRepository.findOne(group.id) != null) {
            throw Exception("Group with such id already exists")
        }
        if (group.name.isNullOrBlank()) throw Exception("Group name cannot be empty")
        if (groupRepository.findByName(group.name) != null) {
            throw Exception("Group with such name already exists")
        }
        userDAO.findOne(group.owner.userId)
                ?: throw Exception("Owner does not exist")
        if (!group.members.isEmpty()) {
            throw Exception("Member list must be blank on create")
        }
        for (i in group.invites) {
            userDAO.findOne(i)
                    ?: throw Exception("Invited user does not exist")
        }

        //create and add conversation
        val conv: Conversation = Conversation()
        conversationDAO.save(conv)
        group.conversationId = conv.id

        //invite users
        for (i in group.invites) {
            groupInviteDAO.save(Invite(subjId = group.owner.userId, objId = i, groupId = group.id))

        }

        return groupRepository.save(group)
    }

    fun deleteById(id: Long) {
        return groupRepository.deleteById(id)
    }

    fun edit(group: Group): Group? {
        //verify properties
        if (group.name.isNullOrBlank()) throw Exception("Group name cannot be empty")
        val possibleGroupWithSameName: Group? = groupRepository.findByName(group.name)
        /*if ((possibleGroupWithSameName != null)) {                                       //same name??????
            throw Exception("Group with such name already exists")
        } else if(possibleGroupWithSameName === group) {
            throw Exception("Group with such name already exists")
        }*/
        userDAO.findOne(group.owner.userId)
                ?: throw Exception("Owner does not exist")

        //create invites
        group.invites
                .filter { groupInviteDAO.findByObjId(it) == null }
                .forEach { groupInviteDAO.save(Invite(subjId = group.owner.userId, objId = it, groupId = group.id)) }
        //delete invites ???????????////
        //inviteDAO.findByGroupId(group.id)

        /*val inviteForGroup : MutableIterable<Invite>? = inviteDAO.findByGroupId(group.id)
        if(inviteForGroup!=null) {
            for(i in inviteForGroup) {
                if(i.id)
            }
        }*/


        return groupRepository.save(group)
    }

    fun  findOne(groupId: Long): Group? {
        return groupRepository.findOne(groupId)
    }
}