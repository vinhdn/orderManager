package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.ShipPartner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShipPartnerRepository: JpaRepository<ShipPartner, String> {
}