package cz.uhk.ppro.inzeraty.repository.jpa;

import cz.uhk.ppro.inzeraty.model.Advert;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.hibernate.Session;
import org.hibernate.Version;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List search(String text) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Advert.class).get();

        Query query = queryBuilder
                .phrase()
                .onField("name")
                .sentence(text)
                .createQuery();

       FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Advert.class);

        @SuppressWarnings("unchecked")
                List results = jpaQuery.getResultList();
        System.out.println(results);

        return results;

    }

}
