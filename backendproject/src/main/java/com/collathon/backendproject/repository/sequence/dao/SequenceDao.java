package com.collathon.backendproject.repository.sequence.dao;

import com.collathon.backendproject.repository.sequence.entity.Sequence;
import com.collathon.backendproject.repository.sequence.exception.SequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao {
    @Autowired
    MongoOperations mongoOperations;

    public long getNextUserId(String key) {
        Query query = new Query(Criteria.where("_id").is(key));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(true);

        Sequence sequence = this.mongoOperations.findAndModify(query, update, findAndModifyOptions, Sequence.class);

        if (sequence == null) {
            throw new SequenceException("Unable to get sequence id for key : " + key);
        }

        return sequence.getSeq();
    }
}
