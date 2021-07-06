package com.vinhdn.order.manager.service

import com.vinhdn.order.manager.entity.Product
import com.vinhdn.order.manager.entity.ProductPhoto
import com.vinhdn.order.manager.repository.ProductPhotoRepository
import com.vinhdn.order.manager.repository.ProductRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*


@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productPhotoRepository: ProductPhotoRepository

    val uploadDir = "src/main/resources/uploads"

    suspend fun findProductByName(query: String) = mono {
        productRepository.findAllByNameContains(query)
    }

    suspend fun findAllProduct() = mono {
        productRepository.findAll()
    }

    suspend fun productById(id: String) = mono {
        productRepository.findById(id)
    }

    suspend fun createProduct(product: Product) = mono {
        productRepository.save(product)
    }

    suspend fun deleteProduct(product: Product) = mono {
        productRepository.delete(product)
    }

    suspend fun updateProduct(product: Product) = mono {
        productRepository.save(product)
    }

    suspend fun uploadImage(productId: String, file: MultipartFile) = mono {
        val uuid = UUID.randomUUID()
        val fullName = StringUtils.cleanPath(file.originalFilename!!)
        val lastIndex = fullName.lastIndexOf('.')
        val fileExtension = fullName.substring(lastIndex + 1)

        val copyLocation: Path =
            Paths.get(uploadDir + File.separator + uuid.toString() + "." + fileExtension)
        val result = saveFile(file.inputStream, copyLocation)
        if(result > 0) {
            productPhotoRepository.save(ProductPhoto(productId = productId, link = uuid.toString()))
        } else {
            null
        }
    }

    private suspend fun saveFile(inputStream: InputStream, copyLocation: Path) = withContext(Dispatchers.IO) {
        Files.copy(inputStream, copyLocation, StandardCopyOption.REPLACE_EXISTING)
    }
}