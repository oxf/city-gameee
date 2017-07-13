package cz.visualio.citygame.model

import cz.visualio.citygame.utlis.IdIncrementer
import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
val idIncInvite = IdIncrementer()

data class Invite(
        var id: Long = idIncInvite.getNextId(),
        val subjId: Long,
        val objId: Long,
        val groupId: Long,
        var status: Boolean? = null                //null - unknown, true - accepted, false - declined
) {
    fun accept() {
        //add user to group, remove invite
    }
    fun decline() {
        //remove invite
    }
}