package com.example.SurviveProject

data class User(
    val username: String? = null,
    val email: String? = null,
    val usertype: Int? = null,
    var gold: Int,
    var score: Int,
    var activeAvatar: Int,
    var avatarInvent: List<Int>,
    var achievementList: List<Int>,
    var level: List<String>,
    var module: List<String>,
    var updatescore: Int,
    var level1score :  Int,
    var level2score :  Int,
    var level3score :  Int,
    var level4score :  Int,
    var level5score :  Int,
)

data class shopList(
    var itemname: String,
    var price: Int,
    var imagebit: Int,
    var imgid: Int

)

data class avatarList(
    var itemname: String,
    var imagebit: Int,
    var imgid: Int
)


data class userLeaderboard(
    var username: String,
    var score: Int,
    var activeAvatar: Int
)


data class gameplayquiz(
    var soal: String,
    var rightanswer: String,
    var optiona: String,
    var optionb: String,
    var optionc: String,
    var optiond: String
)

data class userAchievement(
    var achievementname: String,
    var achievementdesc : String,
    var imagebit: Int,
    var imgid: Int,
)