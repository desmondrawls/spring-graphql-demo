package com.example.DemoGraphQL.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.DemoGraphQL.model.Author
import com.example.DemoGraphQL.model.Book
import com.example.DemoGraphQL.repository.AuthorRepository
import com.example.DemoGraphQL.repository.BookRepository
import org.springframework.data.domain.PageRequest

class Query(private val authorRepository: AuthorRepository, private val bookRepository: BookRepository) : GraphQLQueryResolver {

    fun findAllBooks(): Iterable<Book> {
        return bookRepository.findAll(PageRequest.of(0, 2))
    }

    fun findAllAuthors(): Iterable<Author> {
        return authorRepository.findAll(PageRequest.of(1, 2))
    }

    fun countBooks(): Long {
        return bookRepository.count()
    }

    fun countAuthors(): Long {
        return authorRepository.count()
    }
}
