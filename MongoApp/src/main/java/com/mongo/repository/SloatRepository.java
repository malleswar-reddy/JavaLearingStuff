package com.mongo.repository;

import com.mongo.model.Sloat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SloatRepository extends MongoRepository< Sloat,Long > {
}
