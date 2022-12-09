package com.finalProject.demo.repositories.Accounts;

import com.finalProject.demo.models.Accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChekingRepository extends JpaRepository<Checking, Long> {
}
