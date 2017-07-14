package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.Group
import cz.visualio.citygame.model.Member
import cz.visualio.citygame.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/12/17.
 */
@Repository
interface GroupRepository: MongoRepository<Group, Long> {
    fun findByOwner(ownerId: Long)
    fun deleteById(id: Long)
    fun  findByName(name: String): Group?
}