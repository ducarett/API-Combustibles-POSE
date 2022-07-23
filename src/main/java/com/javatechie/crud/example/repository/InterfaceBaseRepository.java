package com.javatechie.crud.example.repository;


import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface InterfaceBaseRepository<E, ID extends Serializable> extends JpaRepository<E, ID> {


}
