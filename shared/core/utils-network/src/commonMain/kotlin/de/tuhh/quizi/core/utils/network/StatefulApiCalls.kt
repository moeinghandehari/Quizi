package de.tuhh.quizi.core.utils.network

import de.tuhh.quizi.core.utils.loading.ErrorReason
import de.tuhh.quizi.core.utils.loading.ErrorReason.Api.Explanation
import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.core.utils.model.GenericErrorModel
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Forbidden
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.isSuccess
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow

/**
 * Creates a Flow of Events that occur during executing the specified api call.
 * It handles exceptions that might occur and allows to transform the result of a successful
 * response.
 */
inline fun <reified T, R> statefulApiCall(
    typeInfo: TypeInfo = typeInfo<T>(),
    noinline transform: suspend (T) -> (R),
    noinline apiCallFun: suspend () -> HttpResponse,
): Flow<LoadingEvent<R>> {
    return statefulApiCall(
        responseHandler = DefaultResponseHandler(
            typeInfo = typeInfo,
            transform = transform,
        ),
        apiCallFun = apiCallFun,
    )
}

/**
 * Creates a Flow of Events that occur during executing the specified api call.
 * It handles exceptions that might occur and gives full control over the response handling.
 */
fun <T, R> statefulApiCall(
    responseHandler: ResponseHandler<T, R>,
    apiCallFun: suspend () -> HttpResponse,
): Flow<LoadingEvent<R>> = flow {
    emit(LoadingEvent.Loading)
    val resultEvent = try {
        val response = apiCallFun()
        responseHandler.handleResponse(response)
    } catch (e: Exception) {
        e.printStackTrace()
        val errorReason = when (e) {
            is IOException -> ErrorReason.NetworkConnection
            else -> ErrorReason.Unspecified(e.message)
        }
        LoadingEvent.Error(errorReason)
    }
    emit(resultEvent)
}.distinctUntilChanged()

fun interface ResponseHandler<T, R> {
    suspend fun handleResponse(
        response: HttpResponse,
    ): LoadingEvent.Result<R>
}

open class DefaultResponseHandler<T, R>(
    private val typeInfo: TypeInfo,
    private val transform: suspend (T) -> (R),
) : ResponseHandler<T, R> {

    private val unspecifiedError
        get() = LoadingEvent.Error(ErrorReason.Unspecified(null))

    override suspend fun handleResponse(
        response: HttpResponse,
    ): LoadingEvent.Result<R> {
        val status = response.status
        return when {
            status.isSuccess() -> {
                val body = response.body<T>(typeInfo) ?: return unspecifiedError
                LoadingEvent.Success(transform(body))
            }

            status == Forbidden -> {
                val descriptionTextForUser =
                    response.getDescriptionTextForUser().takeUnless { it.isEmpty() }

                LoadingEvent.Error(ErrorReason.Api.Forbidden(descriptionTextForUser))
            }

            status == NotFound || status == InternalServerError || status == BadRequest -> {
                val descriptionTextForUser = response.getDescriptionTextForUser()

                if (descriptionTextForUser.isNotEmpty()) {
                    LoadingEvent.Error(Explanation(descriptionTextForUser))
                } else {
                    unspecifiedError
                }
            }

            else -> {
                unspecifiedError
            }
        }
    }

    private suspend fun HttpResponse.getDescriptionTextForUser() =
        body<GenericErrorModel>().descriptionTextForUser
}