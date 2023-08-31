package vttp2023.batch3.csf.assessment.cnserver.repositories;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vttp2023.batch3.csf.assessment.cnserver.models.News;

import java.util.List;

@Repository
public interface NewsRepository extends MongoRepository<News, Integer> {

	// TODO: Task 2
    @Aggregation(pipeline = {
            "{$match: {postDate: {$gte: ?0}}}",
            "{$unwind: '$tags'}",
            "{$group: {_id: '$tags', count: {$sum: 1}}}",
            "{$sort: {count: -1}}",
            "{$group: {_id: null, tags: {$push: {tag: '$_id', count: '$count'}}}}",
            "{$limit: 10}"
    })
	// Write the native Mongo query in the comment above the method
    List<?> findTagsByTime(long duration);


	// TODO: Task 3
	List<News> findAllByTagsContainsAndPostDateGreaterThanOrderByPostDateDesc(String tag,long duration);

}
