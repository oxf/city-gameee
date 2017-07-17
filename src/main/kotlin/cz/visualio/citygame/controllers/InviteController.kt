package cz.visualio.citygame.controllers

import cz.visualio.citygame.dao.InviteDAO
import cz.visualio.citygame.model.Invite
import cz.visualio.citygame.model.Member
import cz.visualio.citygame.repositories.GroupRepository
import cz.visualio.citygame.repositories.InviteRepository
import cz.visualio.citygame.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

/**
 * Created by stanislav on 7/13/17.
 * Provides REST API for getting and editing invites
 * creating is NOT ALLOWED, to create INVITE, edit GROUP, adding USERs ID to INVITES list in GROUP
 */

@RestController
class InviteController() {
    val logger: Logger = Logger.getLogger("InviteController")

    @Autowired
    private lateinit var inviteDAO: InviteDAO
    /**
     * @return list of INVITEs that have as USER with
     * @param id as object
     */
    @GetMapping("/invite/{id}")
    fun getInvitesForUser(@PathVariable id : Long): MutableIterable<Invite>? {
        logger.log(LogRecord(Level.INFO, "applied GET to all invites for user with id = "+id))
        return inviteDAO.findByObjId(id)
    }

    /**
     * EDITs INVITE, in case of accept (resp. decline) DELETEs INVITE from DB and add USER to GROUP as MEMBER (resp. not adding)
     * any other changes are not allowed, for INVITEing another USER edit group, adding USERs ID to invites list
     */
    @PutMapping("/invite/{id}")
    fun editInvite(
            @PathVariable id : Long,
            @RequestBody invite: Invite
    ): Invite? {
        return inviteDAO.edit(invite, id)
    }
}