package br.pucpr.authserver.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository: JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u" +
        " JOIN u.roles r" +
        " WHERE r.name = :role" +
        " ORDER BY u.name")
    fun findAllByRole(role: String): List<User>

    fun findByEmail(email: String): User?
}