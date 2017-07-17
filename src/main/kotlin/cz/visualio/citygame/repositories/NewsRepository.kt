package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.News
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 * Created by stanislav on 7/11/17.
 */
@Repository
interface NewsRepository : PagingAndSortingRepository<News, Long> {
    fun deleteById(id: Long)
}