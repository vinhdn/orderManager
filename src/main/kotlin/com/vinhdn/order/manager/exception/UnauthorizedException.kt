package com.vinhdn.order.manager.exception

import javax.servlet.ServletException

class UnauthorizedException(message: String?, rootCause: Throwable? = null): ServletException(message, rootCause)