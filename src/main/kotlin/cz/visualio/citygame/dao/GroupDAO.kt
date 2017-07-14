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
    private lateinit var inviteDAO: InviteDAO
    @Autowired
    private lateinit var userRepository: UserRepository

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
        userRepository.findOne(group.owner.userId)
                ?: throw Exception("Owner does not exist")
        if (!group.members.isEmpty()) {
            throw Exception("Member list must be blank on create")
        }
        for (i in group.invites) {
            userRepository.findOne(i)
                    ?: throw Exception("Owner does not exist")
        }

        //create and add conversation
        val conv: Conversation = Conversation()
        conversationDAO.save(conv)
        group.conversationId = conv.id

        //invite users
        for (i in group.invites) {
            inviteDAO.save(Invite(subjId = group.owner.userId, objId = i, groupId = group.id))

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
        if ((possibleGroupWithSameName != null)) {
            throw Exception("Group with such name already exists")
        } else if(possibleGroupWithSameName === group) {
            throw Exception("Group with such name already exists")
        }

        userRepository.findOne(group.owner.userId)
                ?: throw Exception("Owner does not exist")
        //invite people
        group.invites
                .filter { inviteDAO.findByObjId(it) == null }
                .forEach { inviteDAO.save(Invite(subjId = group.owner.userId, objId = it, groupId = group.id)) }
        //delete invites ???????????////
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