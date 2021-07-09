package com.vinhdn.order.manager.controller

import com.vinhdn.order.manager.entity.Product
import com.vinhdn.order.manager.extension.responseSuccess
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
    suspend fun createProduct(@ModelAttribute product: Product,
                              @RequestPart("files", required = false) files: List<MultipartFile>? = null) = coroutineScope {
        product.id = RandomString.make(15)
        val productResult = productService.createProduct(product).awaitFirstOrNull()
            ?: return@coroutineScope ResponseEntity.badRequest()
        files?.map { async { productService.uploadImage(product.id, it) } }?.awaitAll()
        responseSuccess("data", productResult)
    }

    @GetMapping("")
    suspend fun getAllProduct() = coroutineScope {
        responseSuccess("data", productService.findAllProduct().awaitFirstOrNull())
    }

    @PostMapping("{productId}/modify")
    suspend fun modifyProduct(
        @PathVariable("productId") productId: String,
        @ModelAttribute product: Product,
        @RequestPart("photos", required = false) files: List<MultipartFile>? = null
    ) = coroutineScope {
        files?.map { async { productService.uploadImage(product.id, it) } }?.awaitAll()
        responseSuccess("data", productService.createProduct(product).awaitFirstOrNull())
    }

    @PostMapping("{productId}/photos")
    suspend fun uploadProductPhoto(
        @PathVariable("productId") productId: String,
        @RequestPart("photos", required = false) files: Array<MultipartFile>? = null
    ) = coroutineScope {
        val result = ResponseEntity.ok(files?.map { async { productService.uploadImage(productId, it).awaitFirstOrNull() } }?.awaitAll())
        responseSuccess("data", result)
    }

}