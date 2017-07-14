package cz.visualio.citygame.controllers

import cz.visualio.citygame.dao.UserDAO
import cz.visualio.citygame.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger


@RestController
class UserController() {

    @Autowired
    private lateinit var userDao: UserDAO

    val logger: Logger = Logger.getLogger("UserController")

    @GetMapping("/user")
    fun findAll(): MutableIterable<User>? {
        logger.log(LogRecord(Level.INFO, "Applied GET to ALL users"))
        return userDao.findAll()
    }

    @GetMapping("/user/{id}")
    fun findUser(@PathVariable("id") id: Long): User? {
        logger.log(LogRecord(Level.INFO, "Applied GET to user with id : "+id))
        return userDao.findOne(id)
    }

    @PostMapping("/user/")
    fun addUser(@RequestBody user: User): User? {
        logger.log(LogRecord(Level.INFO, "POSTed user with id : "+user.id+" new user :"+user.toString()))
        return userDao.save(user)
    }

    @DeleteMapping("/user/{id}")
    fun removeUser(@PathVariable("id") id: Long){
        logger.log(LogRecord(Level.INFO, "Applied DELETE to user with id : "+id))
        return userDao.deleteById(id)
    }

    @PutMapping("/user/{id}")
    fun editUser(
            @PathVariable("id") id: Long,
            @RequestBody user: User
    ): User? {
        logger.log(LogRecord(Level.INFO, "Applied PUT to user with id : "+id+" set :"+user.toString()))
        return userDao.edit(user)
    }
}

