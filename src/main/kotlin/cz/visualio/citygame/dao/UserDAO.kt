package cz.visualio.citygame.dao

import cz.visualio.citygame.model.User
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.regex.Pattern

/**
 * Created by stanislav on 7/14/17.
 */
@Repository
class UserDAO {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun findAll(): MutableIterable<User>? {
        return userRepository.findAll()
    }

    fun findOne(id: Long): User? {
        return userRepository.findOne(id)
    }

    fun save(user: User): User? {
        if (userRepository.findOne(user.id) != null) {
            throw Exception("User with such id already exists")
        }
        if (user.username.isNullOrBlank()) throw Exception("Username cannot be empty")
        if (userRepository.findByUsername(user.username) != null) {
            throw Exception("Username is already taken")
        }
        if (user.mail.isNullOrBlank()) throw Exception("Mail cannot be empty")
        if (userRepository.findByMail(user.mail)!=null) {
            throw Exception("User with such email alreay exists")
        }
            if (user.password.isNullOrBlank()) throw Exception("Password cannot be empty")

        return userRepository.save(user)
    }

    fun edit(user: User): User? {
       /* userRepository.findOne(user.id)
                ?: throw Exception("User with such id already exists")*/
        if (user.username.isNullOrBlank()) {
            throw Exception("Username cannot be empty")
        }
        /*userRepository.findByUsername(user.username)
                ?: throw Exception("Username is already taken")
        */
        if (user.mail.isNullOrBlank()) {
            throw Exception("Mail cannot be empty")
        }
        if (!mailIsValid(user.mail)) {
            throw Exception("Mail address not valid")
        }
        userRepository.findByMail(user.mail)
                ?: throw Exception("User with such email already exists")
        if (user.password.isNullOrBlank()) {
            throw Exception("Password cannot be empty")
        }

        return userRepository.save(user)
    }

    private fun mailIsValid(mail: String): Boolean { //doesnt work
        if (mail != null) {
            val p = Pattern.compile("^[A-Za-z].*?@gmail\\.com$")
            val m = p.matcher(mail)
            return m.find()
        }
        return false
    }

    fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }
}