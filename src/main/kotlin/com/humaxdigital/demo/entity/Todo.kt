package com.humaxdigital.demo.entity

import javax.persistence.*

@Entity
@Table(name = "todo")
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var title: String = "",
    var content: String = "",
    @Column(name = "is_deleted")
    var deleted: Boolean = false)