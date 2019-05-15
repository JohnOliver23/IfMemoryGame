package com.example.ifmemorygame.model

import com.google.gson.annotations.SerializedName

data class TeacherList(
    @SerializedName("results")
    var teachers: List<Teacher>
)

data class Teacher (
    var url: String,
    var nome:String,
    var frasemarcante:String,
    var foto : String
)
