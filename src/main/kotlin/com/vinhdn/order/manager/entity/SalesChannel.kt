package com.vinhdn.order.manager.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sales_channel")
data class SalesChannel(
    @Id
    val id: Int = 0,
    val name: String = ""
)
