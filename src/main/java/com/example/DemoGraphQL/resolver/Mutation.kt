package com.example.DemoGraphQL.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.DemoGraphQL.model.Author
import com.example.DemoGraphQL.model.Book
import com.example.DemoGraphQL.repository.AuthorRepository
import com.example.DemoGraphQL.repository.BookRepository

class Mutation(private val authorRepository: AuthorRepository, private val bookRepository: BookRepository) : GraphQLMutationResolver {

    fun newAuthor(firstName: String, lastName: String): Author {
        val author = Author(firstName, lastName)

        authorRepository.save(author)

        return author
    }

    fun newBook(title: String, isbn: String, pageCount: Int?, authorId: Long?): Book {
        val book = Book(title, isbn, pageCount ?: 0)

        bookRepository.save(book)

        return book
    }

    fun deleteBook(id: Long?): Boolean {
        bookRepository.deleteById(id)
        return true
    }

    fun updateBookPageCount(pageCount: Int?, id: Long?): Book {
        val book = bookRepository
                .findById(id)
                .orElseThrow { RuntimeException("The book to be updated (${id}) was found") }
        book.pageCount = pageCount!!
        bookRepository.save(book)
        return book
    }
}
