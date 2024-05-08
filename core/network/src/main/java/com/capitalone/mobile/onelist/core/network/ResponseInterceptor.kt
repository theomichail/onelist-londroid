package com.capitalone.mobile.onelist.core.network

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.toUri().path
        return when {
            path.endsWith("user") -> {
                chain.proceed(request)
                    .newBuilder()
                    .code(200)
                    .message("OK")
                    .body(userJson.toResponseBody("application/json".toMediaType()))
                    .addHeader("content-type", "application/json")
                    .build()
            }
            path.endsWith("todos") -> {
                chain.proceed(request)
                    .newBuilder()
                    .code(200)
                    .message("OK")
                    .body(todosJson.toResponseBody("application/json".toMediaType()))
                    .addHeader("content-type", "application/json")
                    .build()
            }
            else -> {
                Response.Builder()
                    .code(404)
                    .message("Not Found")
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .build()
            }
        }
    }
}

private val userJson = """
    {
        "id": 1,
        "username": "johndoe",
        "firstName": "Theo",
        "lastName": "Doe",
        "dateOfBirth": "1990-01-01"
    }
""".trimIndent()

private val todosJson = """
    [
      {
        "id": 1,
        "text": "Buy milk",
        "isCompleted": true,
        "dateCreated": "2024-01-01"
      },
      {
        "id": 2,
        "text": "Call mum",
        "isCompleted": false,
        "dateCreated": "2024-01-01"
      },
      {
        "id": 3,
        "text": "Go to the gym",
        "isCompleted": false,
        "dateCreated": "2024-01-01"
      },
      {
        "id": 4,
        "text": "Read a book",
        "isCompleted": false,
        "dateCreated": "2024-01-01"
      },
      {
        "id": 5,
        "text": "Clean the house",
        "isCompleted": true,
        "dateCreated": "2024-01-01"
      },
      {
        "id": 6,
        "text": "Do laundry",
        "isCompleted": true,
        "dateCreated": "2024-01-01"
      }
    ]
""".trimIndent()