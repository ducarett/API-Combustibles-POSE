package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.service.interfaz.BaseService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseServiceImpl<E, ID extends Serializable> implements BaseService<E, ID> {

    protected InterfaceBaseRepository<E, ID> interfaceBaseRepository;

    public BaseServiceImpl(InterfaceBaseRepository<E, ID> interfaceBaseRepository) {
        this.interfaceBaseRepository = interfaceBaseRepository;
    }

    /**
     * metodo generico para obtener una lista cpmpleta de una entidad especifica.
     *
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E> entities = interfaceBaseRepository.findAll();
            return entities;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * metodo generico para obtener una entidad por ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = interfaceBaseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * metodo generico para crear(persistir) un entidad nueva.
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity = interfaceBaseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * metodo generico para modificar una entidad.
     *
     * @param id
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = interfaceBaseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = interfaceBaseRepository.save(entity);
            return entityUpdate;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * metodo generico de borrado fisico no logico.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean inactive(ID id) throws Exception {
        try {
            if (interfaceBaseRepository.existsById(id)) {
                interfaceBaseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
