package com.vinhdn.order.manager.extension

import java.util.logging.Logger

val logger: Logger = Logger.getLogger("Response")

fun <T : Any> responseSuccess(key: String?, `object`: T?, message: String?): Map<String?, Any?>? {
    val map: MutableMap<String?, Any?> = HashMap()
    map["status"] = 1
    if (message != null) map["message"] = message
    if (key == "message") {
        map["message"] = `object`
    } else map["data"] = `object`
    logger.info("======================Success==================================")
    logger.info(`object`.toString())
    return map
}
fun responseError(message: String?, status: String): Map<String?, Any?>? {
    val map: MutableMap<String?, Any?> = java.util.HashMap()
    map["status"] = status
    if (message != null) {
        map["message"] = message
        logger.info("======================Error==================================")
        logger.info(message)
    }
    return map
}

fun <T: Any> responseSuccess(key: String? = "data", `object`: T?): Map<String?, Any?>? {
    return responseSuccess(key, `object`, null)
}

fun <T: Any> responseSuccess(message: String): Map<String?, Any?>? {
    return responseSuccess(
        "message",
        message,
        null
    )
}

fun responseError(message: String?): Map<String?, Any?>? {
    return responseError(
        message,
        CtrlStatus.ERR_EXCEPTION
    )
}

fun responseValidate(message: String?): Map<String?, Any?>? {
    return responseError(
        message,
        CtrlStatus.ERR_VALIDATE
    )
}

fun responseNotFound(message: String?): Map<String?, Any?>? {
    return responseError(
        message,
        CtrlStatus.ERR_DATA_NOT_FOUND
    )
}

fun responseTokenInvalid(message: String?): Map<String?, Any?>? {
    return responseError(
        message,
        CtrlStatus.ERR_TOKEN_INVALID
    )
}

fun responseExisted(message: String?): Map<String?, Any?>? {
    return responseError(
        message,
        CtrlStatus.ERR_DATA_EXISTED
    )
}

fun responsePermissionDenied(): Map<String?, Any?>? {
    return responsePermissionDenied("Permission denied")
}

fun responsePermissionDenied(message: String?): Map<String?, Any?>? {
    return responseError(
        message,
        CtrlStatus.ERR_PERMISSION_DENIED
    )
}

/**
 * Controller Status
 */
interface CtrlStatus {
    companion object {
        // Normal
        const val NORMAL = "S0"

        // Input error
        const val ERR_VALIDATE = "E1"

        // Login failed
        const val ERR_WRONG_LOGIN = "E2"

        // Permission Denied
        const val ERR_PERMISSION_DENIED = "E3"

        // Token invalid or expried
        const val ERR_TOKEN_INVALID = "E4"

        // Data notfound
        const val ERR_DATA_NOT_FOUND = "E5"

        // Data existed
        const val ERR_DATA_EXISTED = "E6"

        // Exception
        const val ERR_EXCEPTION = "E7"
    }
}