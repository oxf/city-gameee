package cz.visualio.citygame

import cz.visualio.citygame.model.News
import cz.visualio.citygame.repositories.NewsRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
open class CityGameApplication {
    /*@Bean
    open fun init(repository: NewsRepository) = CommandLineRunner {
        repository.save(News(2, "abc", "abcd", LocalDateTime.now().toLocalDate()))
        repository.save(News(3, "abcde", "abcdef", LocalDateTime.now().toLocalDate()))
    }*/
}

    fun main(args: Array<String>) {
        SpringApplication.run(CityGameApplication::class.java, *args);

    }

