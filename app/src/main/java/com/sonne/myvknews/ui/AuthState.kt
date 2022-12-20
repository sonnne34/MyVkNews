package com.sonne.myvknews.ui

sealed class AuthState {

    object Authorised: AuthState()

    object NotAuthorised: AuthState()

    object Initial: AuthState()
}
