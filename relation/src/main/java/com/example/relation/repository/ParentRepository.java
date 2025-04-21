package com.example.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.relation.entity.cascade.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {

}
