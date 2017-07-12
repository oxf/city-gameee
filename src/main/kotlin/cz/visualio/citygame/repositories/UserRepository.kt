package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.User
import org.springframework.data.repository.CrudRepository

/**
 * Created by stanislav on 7/11/17.
 */
interface UserRepository: CrudRepository<User, Long> {
    fun deleteById(id: Long)
}