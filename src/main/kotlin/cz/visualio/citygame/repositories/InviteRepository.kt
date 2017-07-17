package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.Invite
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/13/17.
 */
@Repository
interface InviteRepository: MongoRepository<Invite, Long> {
    fun findByObjId(objId: Long): MutableIterable<Invite>?
    fun findByGroupId(groupId: Long): MutableIterable<Invite>?
    fun deleteByGroupId(groupId: Long)
}