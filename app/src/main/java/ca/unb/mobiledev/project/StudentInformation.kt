package ca.unb.mobiledev.project

import com.google.gson.annotations.SerializedName

data class StudentInformation(
    @SerializedName("student_id")
    var student_id: String,
    @SerializedName("first_name")
    var first_name: String,
    @SerializedName("middle_name")
    var middle_name: String,
    @SerializedName("last_name")
    var last_name: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("postal_code")
    var postal_code: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("profile_type")
    var profile_type: String
)