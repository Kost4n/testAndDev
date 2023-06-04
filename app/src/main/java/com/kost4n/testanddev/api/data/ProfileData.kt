package com.kost4n.testanddev.api.data

import java.util.Date

data class ProfileData(
    var name: String?,
    var userName: String?,
    var birthday: Date?,
    var city: String?,
    var vk: String?,
    var instagram: String?,
    var status: String?,
    var avatar: String?,
    var id: Long?,
    var last: Date?,
    var online: Boolean?,
    var created: Date?,
    var phone: String?,
    var completedTask: Long?,
    var avatars: Avatars?
)