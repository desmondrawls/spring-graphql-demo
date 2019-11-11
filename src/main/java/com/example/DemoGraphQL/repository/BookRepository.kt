package com.example.DemoGraphQL.repository

import com.example.DemoGraphQL.model.Book
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface BookRepository : PagingAndSortingRepository<Book, Long>
