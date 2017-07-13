package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.News
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository

/**
 * Created by stanislav on 7/11/17.
 */
interface NewsRepository : MongoRepository<News, Long> {
    fun deleteById(id: Long)
}