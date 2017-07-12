package cz.visualio.citygame.model

import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
data class Invite(
        var id: Long = 0,
        val subj: User,
        val obj: User,
        val group: Group,
        var status: Boolean?                //null - unknown, true - accepted, false - declined
)

