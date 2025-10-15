package com.example.testeandroid.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeandroid.repo.createRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.security.MessageDigest

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = createRepository(app.applicationContext)

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val hash = sha256(password)
            val ok = repo.verifyUser(username.trim(), hash)
            _loginState.value = if (ok) LoginState.Success else LoginState.Error("Usuário e/ou Senha incorretos")
        }
    }
    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }

    private fun sha256(input: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(input.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

sealed class LoginState {
    object Idle: LoginState()
    object Loading: LoginState()
    object Success: LoginState()
    data class Error(val message: String): LoginState()
}
