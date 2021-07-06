package com.vinhdn.order.manager.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ship_partner")
data class ShipPartner(
    @Id
    val id: String = "",
    val name: String = ""
)