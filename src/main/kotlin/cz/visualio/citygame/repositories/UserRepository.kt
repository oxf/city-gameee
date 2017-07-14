package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/11/17.
 */
@Repository
interface UserRepository: MongoRepository<User, Long> {
    fun deleteById(id: Long)
    fun findByUsername(username: String): User?
    fun findByMail(mail: String): User?
}