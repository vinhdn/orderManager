package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.ProductPhoto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductPhotoRepository: JpaRepository<ProductPhoto, Int> {
}