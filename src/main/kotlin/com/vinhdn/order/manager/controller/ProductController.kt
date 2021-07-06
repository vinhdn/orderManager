package com.vinhdn.order.manager.controller

import com.vinhdn.order.manager.entity.Product
import com.vinhdn.order.manager.service.ProductService
import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.awaitFirstOrNull
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/v1/api/product")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @PostMapping("create")
    suspend fun createProduct(@RequestBody product: Product,
                              @RequestParam("photos", required = false) files: Array<MultipartFile>? = null) = coroutineScope {
        product.id = RandomString.make(10)
        println(product.id)
        val productResult = productService.createProduct(product).awaitFirstOrNull()
            ?: return@coroutineScope ResponseEntity.badRequest()
        files?.map { async { productService.uploadImage(product.id, it) } }?.awaitAll()
        ResponseEntity.ok(productResult)
    }

    @GetMapping("")
    suspend fun getAllProduct() = coroutineScope {
        ResponseEntity.ok(productService.findAllProduct().awaitFirstOrNull())
    }

    @PostMapping("{productId}/modify")
    suspend fun modifyProduct(
        @PathVariable("productId") productId: String,
        @RequestBody product: Product,
        @RequestParam("photos", required = false) files: Array<MultipartFile>? = null
    ) = coroutineScope {
        files?.map { async { productService.uploadImage(product.id, it) } }?.awaitAll()
        ResponseEntity.ok(productService.createProduct(product).awaitFirstOrNull())
    }

    @PostMapping("{productId}/photos")
    suspend fun uploadProductPhoto(
        @PathVariable("productId") productId: String,
        @RequestParam("photos[]", required = false) files: Array<MultipartFile>? = null
    ) = coroutineScope {
        val result = ResponseEntity.ok(files?.map { async { productService.uploadImage(productId, it).awaitFirstOrNull() } }?.awaitAll())
        result
    }

}