package com.semptian.neo4j.service.serviceImpl;

import com.semptian.neo4j.entity.Actor;
import com.semptian.neo4j.repository.ActorRepository;
import com.semptian.neo4j.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:35
 * @Description
 */
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Override
    public List<Actor> customQuery(String name) {
        return actorRepository.customQuery(name);
    }

    @Override
    public Actor findByName(String name, int depth) {
        return actorRepository.findByName(name, depth);
    }

    @Override
    public Optional<Actor> findOneById(long id, int depth) {
        return actorRepository.findById(id, depth);
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }
}
