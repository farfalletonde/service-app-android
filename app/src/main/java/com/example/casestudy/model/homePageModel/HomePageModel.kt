package com.example.casestudy.model.homePageModel

data class HomePageModel(
    val all_services: List<Service>,
    val popular: List<Service>,
    val posts: List<Post>
)
