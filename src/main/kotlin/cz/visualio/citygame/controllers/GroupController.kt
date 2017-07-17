package cz.visualio.citygame.controllers

import cz.visualio.citygame.dao.GroupDAO
import cz.visualio.citygame.dao.UserDAO
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
 * enable REST service for Group model
 */
@RestController
class GroupController() {

    @Autowired
    private lateinit var groupDAO: GroupDAO
    @Autowired
    private lateinit var userDAO: UserDAO

    val logger: Logger = Logger.getLogger("NewsController")
    /**
     * return all groups
     */
    @GetMapping("/group")
    fun findAll(): MutableIterable<Group>? {
        logger.log(LogRecord(Level.INFO, "Applying GET to ALL groups"))
        return groupDAO.findAll()
    }

    /**
     * find certain group by id
     */
    @GetMapping("/group/{id}")
    fun findGroup(@PathVariable("id") id: Long): Group? {
        logger.log(LogRecord(Level.INFO, "Applying GET to GROUP with id : "+id))
        return groupDAO.findOne(id)
    }

    /**
     * add new group
     */
    @PostMapping("/group/")
    fun postGroup(@RequestBody group: Group): Group? {
        logger.log(LogRecord(Level.INFO, "POSTing new GROUP"))
        return groupDAO.save(group)
    }

    /**
     * delete group by it's id
     * @param id
     * @return Unit
     */
    @DeleteMapping("/group/{id}")
    fun removeGroup(@PathVariable("id") id: Long){
        logger.log(LogRecord(Level.INFO, "DELETEing GROUP with id : "+id))
        return groupDAO.deleteById(id)
    }

    /**
     * edit group
     */
    @PutMapping("/group/{id}")
    fun editGroup(
            @PathVariable("id") id: Long,
            @RequestBody group: Group
    ): Group? {
        logger.log(LogRecord(Level.INFO, "Applying PUT for GROUP with id : "+id))
        return groupDAO.edit(group)
    }

    /**
     * GET all groups created by user with given id
     */
    @GetMapping("/group/ownedBy/{userId}")
    fun getOwnedGroups(@PathVariable("userId") userId: Long): MutableList<Group> {
        val groups: MutableList<Group> = ArrayList<Group>()
        val user: User = userDAO.findOne(userId) ?: throw Exception("User with such id does not exist")
        for(i in user.ownedGroupIds) {
            groups.add(groupDAO.findOne(i)!!)
        }
        return groups
    }

    /**
     * @return all groups that have USER with given
     * @param userId as MEMBER
     */
    @GetMapping("/group/participateBy/{userId}")
    fun getMemberGroup(@PathVariable("userId") id: Long): MutableList<Group> {
        val groups: MutableList<Group> = ArrayList<Group>()
        val user: User = userDAO.findOne(id) ?: throw Exception("User with such id does not exist")
        user.memberedGroupIds.mapTo(groups) { groupDAO.findOne(it)!! }
        return groups
    }
}