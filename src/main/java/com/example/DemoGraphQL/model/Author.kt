package com.example.DemoGraphQL.model

import javax.persistence.*

@Entity
class Author {
    @Id
    @Column(name = "author_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "author_first_name", nullable = false)
    var firstName: String? = null

    @Column(name = "author_last_name", nullable = false)
    var lastName: String? = null

    constructor() {}

    constructor(id: Long?) {
        this.id = id
    }

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val author = o as Author?

        return id == author!!.id
    }

    override fun hashCode(): Int {
        return id!!.hashCode()
    }

    override fun toString(): String {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\''.toString() +
                ", lastName='" + lastName + '\''.toString() +
                '}'.toString()
    }
}
