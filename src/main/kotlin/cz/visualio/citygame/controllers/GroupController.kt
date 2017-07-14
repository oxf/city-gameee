package cz.visualio.citygame.controllers

import cz.visualio.citygame.dao.GroupDAO
import cz.visualio.citygame.model.*
import cz.visualio.citygame.repositories.ConversationRepository
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger


/**
 * Created by stanislav on 7/12/17.
 */
@RestController
class GroupController() {

    @Autowired
    private lateinit var groupDAO: GroupDAO

    val logger: Logger = Logger.getLogger("NewsController")

    @GetMapping("/group")
    fun findAll(): MutableIterable<Group>? {
        logger.log(LogRecord(Level.INFO, "Applying GET to ALL groups"))
        return groupDAO.findAll()
    }

    @PostMapping("/group/")
    fun postGroup(@RequestBody group: Group): Group? {
        logger.log(LogRecord(Level.INFO, "POSTing new GROUP"))
        return groupDAO.save(group)
    }
    @DeleteMapping("/group/{id}")
    fun removeGroup(@PathVariable("id") id: Long){
        logger.log(LogRecord(Level.INFO, "DELETEing GROUP with id : "+id))
        return groupDAO.deleteById(id)
    }
    @PutMapping("/group/{id}")
    fun editGroup(
            @PathVariable("id") id: Long,
            @RequestBody group: Group
    ): Group? {
        logger.log(LogRecord(Level.INFO, "Applying PUT for GROUP with id : "+id))
        return groupDAO.edit(group)
    }

/*    @GetMapping("/group/{id}/ownedBy/")
    fun getOwnedGroups(@PathVariable("id") id: Long) {
        repository.find
    }
    @GetMapping("/group/{id}/memberOfGroups")
    fun getMemberGroup(@PathVariable("id") id: Long) {

    }*/
}