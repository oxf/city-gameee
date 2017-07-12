package cz.visualio.citygame.controllers

import cz.visualio.citygame.model.News
import cz.visualio.citygame.repositories.NewsRepository
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

/**
 * Created by stanislav on 7/11/17.
 */
@RestController
class NewsController(val repository: NewsRepository) {

    val logger: Logger = Logger.getLogger("NewsController")

    @GetMapping("/news")
    fun findAll(): MutableIterable<News>? {
        logger.log(LogRecord(Level.INFO, "Applied GET to ALL news"))
        return repository.findAll()
    }

    @GetMapping("/news/{id}")
    fun findOne(@PathVariable("id") id: Long): News? {
        logger.log(LogRecord(Level.INFO, "Applied GET to news with id : "+id))
        return repository.findOne(id)
    }

    @PostMapping("/news/")
    fun addNews(@RequestBody news: News): News? {
        logger.log(LogRecord(Level.INFO, "POSTed news with id : "+news.id+" new item :"+news.toString()))
        return repository.save(news)
    }

    @DeleteMapping("/news/{id}")
    fun removeNews(@PathVariable("id") id: Long){
        logger.log(LogRecord(Level.INFO, "Applied DELETE to news with id : "+id))
        return repository.deleteById(id)
    }

    @PutMapping("/news/{id}")
    fun editNews(
            @PathVariable("id") id: Long,
            @RequestBody news: News
    ): News? {
        assert(news.id == id)
        logger.log(LogRecord(Level.INFO, "Applied PUT to news with id : "+id+" set :"+news.toString()))
        return repository.save(news)
    }
}