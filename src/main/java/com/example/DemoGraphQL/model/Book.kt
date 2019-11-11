package com.example.DemoGraphQL.model

import javax.persistence.*

@Entity
class Book {
    @Id
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "book_title", nullable = false)
    var title: String? = null

    @Column(name = "book_isbn", nullable = false)
    var isbn: String? = null

    @Column(name = "book_pageCount", nullable = false)
    var pageCount: Int = 0

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    var author: Author? = null

    constructor() {}

    constructor(title: String, isbn: String, pageCount: Int, author: Author? = null) {
        this.title = title
        this.isbn = isbn
        this.pageCount = pageCount
        this.author = author
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val book = o as Book?

        return id == book!!.id
    }

    override fun hashCode(): Int {
        return id!!.hashCode()
    }

    override fun toString(): String {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\''.toString() +
                ", isbn='" + isbn + '\''.toString() +
                ", pageCount=" + pageCount +
                ", author=" + author +
                '}'.toString()
    }
}
