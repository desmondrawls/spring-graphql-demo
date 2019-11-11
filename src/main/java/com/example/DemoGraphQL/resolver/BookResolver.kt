package com.example.DemoGraphQL.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.DemoGraphQL.model.Author
import com.example.DemoGraphQL.model.Book
import com.example.DemoGraphQL.repository.AuthorRepository

class BookResolver(private val authorRepository: AuthorRepository) : GraphQLResolver<Book> {
    fun getAuthor(book: Book): Author {
        return Author("what", "the hell")
    }
}
