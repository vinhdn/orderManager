package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.SalesChannel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesChannelRepository: JpaRepository<SalesChannel, Int> {}