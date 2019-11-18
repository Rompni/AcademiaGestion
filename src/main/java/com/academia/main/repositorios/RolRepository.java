package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.academia.main.entidades.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByRole(String role);
}
