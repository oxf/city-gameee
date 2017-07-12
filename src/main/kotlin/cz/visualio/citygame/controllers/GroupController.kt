package cz.visualio.citygame.controllers

import cz.visualio.citygame.model.Group
import cz.visualio.citygame.model.News
import cz.visualio.citygame.repositories.GroupRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger


/**
 * Created by stanislav on 7/12/17.
 */
@RestController
class GroupController(val repository: GroupRepository) {

    val logger: Logger = Logger.getLogger("NewsController")

    @GetMapping("/group")
    fun findAll(): MutableIterable<Group>? {
        logger.log(LogRecord(Level.INFO, "Applied GET to ALL groups"))
        return repository.findAll()
    }

    @PostMapping("/group/")
    fun postGroup(group: Group): Group? {
        //logger.log(LogRecord(Level.INFO, "created group with id : "+group.id+"name :"+group.name+" by user id :"+group.owner.id))
        return repository.save(group)
    }
}