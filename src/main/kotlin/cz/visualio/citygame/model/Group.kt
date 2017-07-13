package cz.visualio.citygame.model

import cz.visualio.citygame.annotations.NoArg
import cz.visualio.citygame.utlis.IdIncrementer
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


val idIncGroup = IdIncrementer()
@Document(collection = "groupCollection")
@NoArg
data class Group(
        @Id val id: Long = idIncGroup.getNextId(),
        var name: String,
        val ownerId: Long,
        var conversationId: Long,
        val membersIds: List<Long> = ArrayList<Long>()
) {

}
