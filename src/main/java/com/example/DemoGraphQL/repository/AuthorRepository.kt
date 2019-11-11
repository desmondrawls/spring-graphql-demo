package com.example.DemoGraphQL.repository

import com.example.DemoGraphQL.model.Author
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface AuthorRepository : PagingAndSortingRepository<Author, Long>
