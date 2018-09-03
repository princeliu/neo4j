package com.semptian.neo4j.service.serviceImpl;

import com.semptian.neo4j.entity.Director;
import com.semptian.neo4j.repository.DirectorRepository;
import com.semptian.neo4j.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:36
 * @Description
 */
@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public List<Director> customQuery(String name) {
        return directorRepository.customQuery(name);
    }

    @Override
    public Director findByName(String name, int depth) {
        return directorRepository.findByName(name, depth);
    }

    @Override
    public Optional<Director> findOneById(long id, int depth) {
        return directorRepository.findById(id, depth);
    }

    @Override
    public Director save(Director director) {
        return directorRepository.save(director);
    }
}
