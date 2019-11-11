package com.example.DemoGraphQL

import com.example.DemoGraphQL.exception.GraphQLErrorAdapter
import com.example.DemoGraphQL.model.Author
import com.example.DemoGraphQL.model.Book
import com.example.DemoGraphQL.repository.AuthorRepository
import com.example.DemoGraphQL.repository.BookRepository
import com.example.DemoGraphQL.resolver.BookResolver
import com.example.DemoGraphQL.resolver.Mutation
import com.example.DemoGraphQL.resolver.Query
import graphql.ExceptionWhileDataFetching
import graphql.GraphQLError
import graphql.servlet.GraphQLErrorHandler
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import java.util.ArrayList
import java.util.function.Predicate
import java.util.stream.Collectors

@SpringBootApplication
open class DemoGraphQlApplication {

    @Bean
    open fun errorHandler(): GraphQLErrorHandler {
        return object : GraphQLErrorHandler {
            override fun processErrors(errors: List<GraphQLError>): List<GraphQLError> {
                val clientErrors = errors
                        .filter { this.isClientError(it) }

                val serverErrors = errors.stream()
                        .filter { e -> !isClientError(e) }
                        .map { GraphQLErrorAdapter(it) }
                        .collect(Collectors.toList())

                val e = ArrayList<GraphQLError>()
                e.addAll(clientErrors)
                e.addAll(serverErrors)
                return e
            }

            protected fun isClientError(error: GraphQLError): Boolean {
                return !(error is ExceptionWhileDataFetching || error is Throwable)
            }
        }
    }

    @Bean
    open fun bookResolver(authorRepository: AuthorRepository): BookResolver {
        return BookResolver(authorRepository)
    }

    @Bean
    open fun query(authorRepository: AuthorRepository, bookRepository: BookRepository): Query {
        return Query(authorRepository, bookRepository)
    }

    @Bean
    open fun mutation(authorRepository: AuthorRepository, bookRepository: BookRepository): Mutation {
        return Mutation(authorRepository, bookRepository)
    }

    @Bean
    open fun demo(authorRepository: AuthorRepository, bookRepository: BookRepository): CommandLineRunner {
        return CommandLineRunner {
            val author = Author("Herbert", "Schildt")
            val author2 = Author("William", "Shakespear")
            val author3 = Author("Maya", "Angelou")
            val author4 = Author("Toni", "Morrison")
            val author5 = Author("David", "Foster Wallace")
            authorRepository.save(author)
            authorRepository.save(author2)
            authorRepository.save(author3)
            authorRepository.save(author4)
            authorRepository.save(author5)

            bookRepository.save(Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoGraphQlApplication::class.java, *args)
        }
    }
}
