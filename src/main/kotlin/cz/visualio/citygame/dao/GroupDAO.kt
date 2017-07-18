package cz.visualio.citygame.dao

import cz.visualio.citygame.model.Conversation
import cz.visualio.citygame.model.Group
import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.model.User
import cz.visualio.citygame.repositories.ConversationRepository
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/14/17.
 * Data Access Object https://en.wikipedia.org/wiki/Data_access_object
 * for controlling database access for GROUP model
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
    /**
     *
     */
    fun save(group: Group): Group? {
        //verify properties
        if (groupRepository.findOne(group.id) != null) { //find groups with same id
            throw Exception("Group with such id already exists")
        }
        if (group.name.isNullOrBlank()) throw Exception("Group name cannot be empty")
        if (groupRepository.findByName(group.name) != null) {   //find groups with same name
            throw Exception("Group with such name already exists")
        }
        userDAO.findOne(group.owner.userId) //verify owner exist
                ?: throw Exception("Owner does not exist")
        if (!group.members.isEmpty()) { //member list is empty on start
            throw Exception("Member list must be blank on create")
        }
        for (i in group.invites) { // verify invited user exist
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
        //add reference to owner
        val owner : User = userDAO.findOne(group.owner.userId) ?: throw Exception("User with id "+group.owner.userId+" not found")
        owner.addOwnedGroup(group.id)
        userDAO.edit(owner)

        return groupRepository.save(group)
    }

    /**
     * DELETE GROUP with
     * @param id from database, also DELETEs all references for this group from OWNER, MEMBERS and INVITES
     */
    fun deleteById(id: Long) {
        val deletedGroup: Group = groupRepository.findOne(id) ?: throw Exception("Group with id = "+id+" does not exist") //rewrite all exceptinos like this
        //delete reference for owner
        val owner = userDAO.findOne(deletedGroup.owner.userId) ?: throw Exception("Owner of group id = "+id+" not found")
        owner.ownedGroupIds.remove(id)
        userDAO.edit(owner)
        //delete references for members
        for(i in deletedGroup.members) {
            val user: User = userDAO.findOne(i.userId) ?: throw Exception("Member with id "+i.userId+" of group id "+id+" not found")
            user.memberedGroupIds.remove(id)
            userDAO.edit(user)
        }
        //delete invites
        for(i in deletedGroup.invites) {
            groupInviteDAO.deleteByObjId(i)
        }
        return groupRepository.deleteById(id)
    }

    fun edit(group: Group): Group? {
        //verify properties
        if (group.name.isNullOrBlank()) throw Exception("Group name cannot be empty") //name not null or blank
        val possibleGroupWithSameName: Group? = groupRepository.findByName(group.name) //find group with same name
        if (possibleGroupWithSameName?.id == group.id) { //if it's that very same group do nothing
        } else if(possibleGroupWithSameName != null) { //else group with such name exist
            throw Exception("Group with such name already exists")
        }
        if(groupRepository.findOne(group.id).owner.userId !=group.owner.userId) { //if owner is same
            throw Exception("Group owner cant be changed")
        }
        //create invites
        group.invites
                .filter { groupInviteDAO.findByObjId(it) == null } //if?????????????????????
                .forEach { groupInviteDAO.save(Invite(subjId = group.owner.userId, objId = it, groupId = group.id)) }
        //delete invites
        val invitesToGroup: MutableIterable<Invite>? = groupInviteDAO.findByGroupId(group.id)
        invitesToGroup?.filterNot { group.invites.contains(it.objId) }?.forEach { groupInviteDAO.deleteOne(it.id) }
        return groupRepository.save(group)
    }

    fun  findOne(groupId: Long): Group? {
        return groupRepository.findOne(groupId)
    }
}