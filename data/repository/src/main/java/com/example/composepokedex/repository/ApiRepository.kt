package com.example.composepokedex.repository

import com.example.composepokedex.model.model.NetworkException
import com.example.composepokedex.model.model.PokeDexApiException
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.model.UndefinedException
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ApiRepository {
    suspend fun <T> execute(block: suspend () -> Result<T, PokeDexApiException>): Result<T?, PokeDexException> {
        return try {
            block.invoke()
        } catch (e: SocketTimeoutException) {
            Err(NetworkException(e))
        } catch (e: UnknownHostException) {
            Err(NetworkException(e))
        } catch (e: Exception) {
            Err(UndefinedException(e))
        }
    }
}