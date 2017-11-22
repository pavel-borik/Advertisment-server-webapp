package cz.uhk.ppro.inzeraty.repository;

import cz.uhk.ppro.inzeraty.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
