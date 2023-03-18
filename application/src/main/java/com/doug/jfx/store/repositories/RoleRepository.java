package com.doug.jfx.store.repositories;

import com.doug.jfx.store.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT role FROM Role role WHERE role.authority IN :names")
    List<Role> findAllByNames(@Param("names") List<String> names);

}
