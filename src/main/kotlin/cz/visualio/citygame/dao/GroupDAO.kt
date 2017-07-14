package cz.visualio.citygame.dao

import cz.visualio.citygame.model.Conversation
import cz.visualio.citygame.model.Group
import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.repositories.ConversationRepository
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
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
    private lateinit var conversationRepository: ConversationRepository
    @Autowired
    private lateinit var inviteRepository: InviteRepository

    fun findAll(): MutableIterable<Group>? {
        return groupRepository.findAll()
    }

    fun save(group: Group): Group? {
        //create and add conversation
        val conv: Conversation = Conversation()
        conversationRepository.save(conv)
        group.conversationId = conv.id
        //invites
        for (i in group.invites) {
            inviteRepository.save(Invite(subjId = group.owner.userId, objId = i, groupId = group.id))

        }
        return groupRepository.save(group)

    }

    fun  deleteById(id: Long) {
        return groupRepository.deleteById(id)
    }

    fun  edit(group: Group): Group? {
        group.invites
                .filterNot { group.invites.contains(it) }
                .forEach { inviteRepository.save(Invite(subjId = group.owner.userId, objId = it, groupId = group.id)) }
        return groupRepository.save(group)

    }
}