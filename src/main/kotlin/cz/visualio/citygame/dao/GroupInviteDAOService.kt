package cz.visualio.citygame.dao

import cz.visualio.citygame.model.Group
import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by stanislav on 7/17/17.
 */
@Service
class GroupInviteDAOService {
    @Autowired
    private lateinit var inviteRepository: InviteRepository
    @Autowired
    private lateinit var groupRepository: GroupRepository

    fun  findOne(groupId: Long): Group? {
        return groupRepository.findOne(groupId)
    }

    fun  save(group: Group): Group? {
        return groupRepository.save(group)
    }

    fun  save(invite: Invite): Invite? {
        return inviteRepository.save(invite)
    }

    fun  findByObjId(id: Long): MutableIterable<Invite>? {
        return inviteRepository.findByObjId(id)
    }

}