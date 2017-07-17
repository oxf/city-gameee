package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.Message
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by stanislav on 7/17/17.
 */interface MessageRepository: PagingAndSortingRepository<Message, Long> {

}