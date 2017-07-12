package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.News
import org.springframework.data.repository.CrudRepository

/**
 * Created by stanislav on 7/11/17.
 */
interface NewsRepository : CrudRepository<News, Long> {
    fun deleteById(id: Long)
}