package cz.visualio.citygame.model

import cz.visualio.citygame.utlis.IdIncrementer
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
val idIncUser: IdIncrementer = IdIncrementer()
@Document(collection = "user")
data class User(
        @Id var id: Long = idIncUser.getNextId(),
        var username: String = "", //unique, not empty
        var mail: String = "", //validate, not empty
        var password : String = "", //not empty
        val ownedGroupIds: MutableList<Long> = ArrayList<Long>(),
        val memberedGroupIds: MutableList<Long> = ArrayList<Long>()
) {
    fun addOwnedGroup(id: Long) {
        ownedGroupIds.add(id)
    }
    fun addMemberedGroup(id: Long) {
        memberedGroupIds.add(id)
    }
}