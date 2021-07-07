package com.vinhdn.order.manager.context

import com.vinhdn.order.manager.entity.Shop
import com.vinhdn.order.manager.entity.User
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@RequestScope
@Component
data class CommonContext(
    var user: User? = null,
    var shop: Shop? = null
)
