package com.example.jfilmsspring.models


import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*

@Table(name = "users")
data class Usuario(
    @Id
    val id :Int?=null,
    @Column("uuid")
    val uuid:String= UUID.randomUUID().toString(),
    @Column("email")
    val email:String,
    @Column("name")
    val name:String,
    @Column("dni")
    val dni:String,
    @get:JvmName("userPassword")
    @Column("password")
    val password:String,
    @Column("rol")
    val rol :String= TypeRol.USER.name,
    @Column("avaliable")
    val avaliable:Boolean=true,
    @Column("tarjeta")
    val tarjeta: String,
    @Column("monto")
    val monto: Double,
    val deleted: Boolean = false,
): UserDetails {


    enum class TypeRol() {
        USER,ADMIN
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

        return rol.split(",").map { SimpleGrantedAuthority("ROLE_${it.trim()}") }.toMutableList()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


}






