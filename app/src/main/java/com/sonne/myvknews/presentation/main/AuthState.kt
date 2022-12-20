package com.sonne.myvknews.presentation.main

sealed class AuthState {

    object Authorised: AuthState()

    object NotAuthorised: AuthState()

    object Initial: AuthState()
}
