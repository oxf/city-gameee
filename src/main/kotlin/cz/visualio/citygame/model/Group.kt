package cz.visualio.citygame.model

import cz.visualio.citygame.annotations.NoArg
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
@Document
@NoArg
data class Group(
        var id: Long,
        val owner: User,
        var name: String/*,
        val members: ArrayList<User> = ArrayList<User>(),
        val conversation: Conversation*/
) {

}
