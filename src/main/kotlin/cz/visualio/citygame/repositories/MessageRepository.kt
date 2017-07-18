package cz.visualio.citygame.repositories

import cz.visualio.citygame.model.Message
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by stanislav on 7/17/17.
 * I chose PagingAndSortingRepository for comfortable sorting by publication time
 */
interface MessageRepository: PagingAndSortingRepository<Message, Long> {

}