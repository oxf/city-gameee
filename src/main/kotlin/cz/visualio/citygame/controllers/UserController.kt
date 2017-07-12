package cz.visualio.citygame.controllers

import cz.visualio.citygame.model.User
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger


@RestController
class UserController(val repository: UserRepository) {
    val logger: Logger = Logger.getLogger("UserController")

    @GetMapping("/user")
    fun findAll(): MutableIterable<User>? {
        logger.log(LogRecord(Level.INFO, "Applied GET to ALL users"))
        return repository.findAll()
    }

    @GetMapping("/user/{id}")
    fun findUser(@PathVariable("id") id: Long): User? {
        logger.log(LogRecord(Level.INFO, "Applied GET to user with id : "+id))
        return repository.findOne(id)
    }

    @PostMapping("/user/")
    fun addUser(@RequestBody user: User): User? {
        logger.log(LogRecord(Level.INFO, "POSTed user with id : "+user.id+" new user :"+user.toString()))
        return repository.save(user)
    }

    @DeleteMapping("/user/{id}")
    fun removeUser(@PathVariable("id") id: Long){
        logger.log(LogRecord(Level.INFO, "Applied DELETE to user with id : "+id))
        return repository.deleteById(id)
    }

    @PutMapping("/user/{id}")
    fun editUser(
            @PathVariable("id") id: Long,
            @RequestBody user: User
    ): User? {
        assert(user.id == id)
        logger.log(LogRecord(Level.INFO, "Applied PUT to user with id : "+id+" set :"+user.toString()))
        return repository.save(user)
    }
}

