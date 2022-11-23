package com.mongo;

import com.mongo.model.DatabaseSequence;
import com.mongo.model.Sloat;
import com.mongo.repository.SloatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;



@SpringBootTest
class MongoAppApplicationTests {

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private SloatRepository repository;
	@Test
	void contextLoads() {
		final List< Sloat > sloatList = IntStream.range(1, 10)
				.peek(e-> System.out.println("Processed  "+e))
				.mapToObj((i) -> new Sloat(generateSequence("Sloat" + i), "Sloat" + i))
				.collect(Collectors.toList());

		sloatList.forEach(data -> repository.save(data));
//		repository.save(sloat);
		repository.findAll().forEach(System.out::println);

	}

	public long generateSequence(String seqName) {

		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq",1), options().returnNew(true).upsert(true),
				DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;

	}
}
