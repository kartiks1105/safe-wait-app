package ca.unb.mobiledev.project

import com.google.gson.annotations.SerializedName

data class Credential(
    @SerializedName("student_id")
    var student_id: String,
    @SerializedName("password")
    var password: String
)