package cz.visualio.citygame.model

import cz.visualio.citygame.annotations.NoArg
import cz.visualio.citygame.utlis.IdIncrementer
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

val idIncGroup = IdIncrementer()
@Document(collection = "groupCollection")
@NoArg
data class Group(
        @Id val id: Long = idIncGroup.getNextId(),
        var name: String, //not empty
        val owner: Member, //valid
        var conversationId: Long,
        val members: MutableList<Member> = ArrayList<Member>(),
        val invites: MutableList<Long> = ArrayList<Long>() //valid members
) {
    fun addMember(member: Member) {
        members.add(member)
    }
}

data class Member(
        val userId: Long,
        var turnOnNotification: Boolean = true,
        var shareLocation: Boolean = true
) {

}