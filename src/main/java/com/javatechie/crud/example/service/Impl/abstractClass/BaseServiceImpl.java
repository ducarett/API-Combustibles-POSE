package com.javatechie.crud.example.service.Impl.abstractClass;

import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.service.interfaz.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseServiceImpl<E, ID extends Serializable> implements BaseService<E, ID> {

    private static final String NO_HUBO_RESULTADOS = "No hubo resultados";
    private static final String NO_EXIST = "No existe este usuario!";
    protected InterfaceBaseRepository<E, ID> interfaceBaseRepository;

    public BaseServiceImpl(InterfaceBaseRepository<E, ID> interfaceBaseRepository) {
        this.interfaceBaseRepository = interfaceBaseRepository;
    }

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

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            Page<E> entities = interfaceBaseRepository.findAll(pageable);
            return entities;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E getById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = interfaceBaseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

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

    @Override
    public Page<E> listGeneric(Page<E> list) throws Exception {
        try {
            if (list.isEmpty()) throw new Exception(NO_HUBO_RESULTADOS);
            return list;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public E getEntity(E entity) throws Exception {
        try {
            if (Objects.isNull(entity)) throw new Exception(NO_EXIST);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
