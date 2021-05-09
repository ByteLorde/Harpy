package com.harpy.harpyserver.repository;


import com.harpy.harpyserver.entity.AccountMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountMetadataRepository extends JpaRepository<AccountMetadata, String> {

}
