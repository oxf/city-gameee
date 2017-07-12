package cz.visualio.citygame.model

import cz.visualio.citygame.annotations.NoArg
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
@Document(collection = "group")
@NoArg
data class Group(
        @Id var id: Long = 0,
        val owner: User,
        var name: String = ""/*,
        val members: ArrayList<User> = ArrayList<User>(),
        val conversation: Conversation*/
) {

}
