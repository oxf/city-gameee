package cz.visualio.citygame.model

import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
data class Point(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        val owner: User,
        var name: String,
        var description: String,
        var type: PointType,
        var lng: Float,
        var lat: Float,
        var geohash: String,
        val groups: List<Group>)
{


}
