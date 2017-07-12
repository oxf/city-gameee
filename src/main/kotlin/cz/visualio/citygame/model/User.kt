package cz.visualio.citygame.model

import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
data class User(
        var id: Long = 0,
        var username: String = "",
        var mail: String = "",
        var password : String = ""
)