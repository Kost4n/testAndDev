package com.kost4n.testanddev.api

import com.kost4n.testanddev.api.data.Avatars
import com.kost4n.testanddev.api.data.ProfileData

data class ResponseSendCode(
    var is_success: Boolean?
)


data class ResponseCheckCode(
    var refresh_token: String?,
    var access_token: String?,
    var user_id: Long?,
    var is_user_exist: Boolean?
)

data class ResponseRegistration(
    var refresh_token: String?,
    var access_token: String?,
    var user_id: Long?
)

data class ResponseRefreshToken(
    var refresh_token: String?,
    var access_token: String?,
    var user_id: Long?
)

data class ResponseCheckJwt(
    var string: String?
)

data class ResponseGetCurrentUser(
    var profileData: ProfileData?
)

data class ResponseUpdateUser(
    var avatars: Avatars?
)