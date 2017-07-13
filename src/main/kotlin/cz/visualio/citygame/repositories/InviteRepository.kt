package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.Invite
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by stanislav on 7/13/17.
 */
interface InviteRepository: MongoRepository<Invite, Long> {
    fun findByObjId(objId: Long): MutableIterable<Invite>?
}