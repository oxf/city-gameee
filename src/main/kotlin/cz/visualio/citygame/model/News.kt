package cz.visualio.citygame.model

import cz.visualio.citygame.annotations.NoArg
import org.springframework.data.annotation.Id
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Created by stanislav on 7/11/17.
 */
@Entity
@NoArg
data class News(
        var id: Long,
        var title: String = "",
        var body: String = "",
        //var imageURL: String? = "",
        val date: LocalDate = LocalDate.now()
)