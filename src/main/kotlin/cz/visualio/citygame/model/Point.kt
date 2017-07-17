package cz.visualio.citygame.model

import cz.visualio.citygame.utlis.IdIncrementer
import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
val idIncPoint: IdIncrementer = IdIncrementer()
data class Point(
        @Id val id: Long = idIncPoint.getNextId(),
        val owner: User, //validate exist
        var name: String, //not null, not empty
        var description: String,
        var type: PointType, //valid
        var lng: Float, //
        var lat: Float,
        var geohash: String,
        val groups: List<Long>)
{


}
