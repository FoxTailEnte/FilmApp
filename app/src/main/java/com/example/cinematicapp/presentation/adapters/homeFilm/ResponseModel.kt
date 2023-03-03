package com.example.cinematicapp.presentation.adapters.homeFilm

data class ResponseModel (
    val docs: List<ResponseModel2>
    )
data class ResponseModel2 (
    val name: String,
    val poster: ResponseModel3
        )
data class ResponseModel3 (
    val previewUrl: String
        )
