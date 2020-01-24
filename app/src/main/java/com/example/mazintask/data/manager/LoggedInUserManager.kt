package com.example.mazintask.data.manager

import com.example.mazintask.data.models.User
import com.example.mazintask.util.Utils
import java.util.*


object LoggedInUserManager : Observable() {

    var user: User? = null
        get() {
            if (field == null) {
                field = Utils().userFromMemory
            }
            return field
        }
        set(value) {
            field = value
            Utils().saveUserInMemory(value)
            setChanged()
            notifyObservers(value)
        }

    fun isUserLoggedIn(): Boolean {
        return user != null
    }
}
