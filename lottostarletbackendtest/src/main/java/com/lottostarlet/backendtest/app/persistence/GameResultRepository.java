package com.lottostarlet.backendtest.app.persistence;

import com.lottostarlet.backendtest.app.model.GameResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GameResultRepository extends MongoRepository<GameResult, String> {

    GameResult findById(String id);
    List<GameResult> findAll();
}
