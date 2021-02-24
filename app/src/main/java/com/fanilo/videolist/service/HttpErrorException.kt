package com.fanilo.videolist.service

class HttpErrorException(val httpCode: Int, message: String?) : Exception(message)