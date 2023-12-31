package br.pucpr.authserver.users

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class UsersBootstrap(
    val rolesRepository: RolesRepository,
    val userRepository: UsersRepository
): ApplicationListener<ContextRefreshedEvent>  {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole = Role(name = "ADMIN")
        if (rolesRepository.count() == 0L) {
           rolesRepository.save(adminRole)
           rolesRepository.save(Role(name = "USER"))
        }
        if (userRepository.count() == 0L){
            val admin = User(
                email = "admin@authserver.com",
                name = "admin",
                password = "Auth Server Administrador"
            )
            admin.roles.add(adminRole)
            userRepository.save(admin)
        }
    }
}