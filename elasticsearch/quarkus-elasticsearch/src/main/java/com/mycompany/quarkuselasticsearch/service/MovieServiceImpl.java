package com.mycompany.quarkuselasticsearch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.quarkuselasticsearch.exception.MovieServiceException;
import com.mycompany.quarkuselasticsearch.mapper.MovieMapper;
import com.mycompany.quarkuselasticsearch.model.Movie;
import com.mycompany.quarkuselasticsearch.rest.dto.SearchMovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class MovieServiceImpl implements MovieService {

    @Inject
    RestHighLevelClient client;

    @Inject
    MovieMapper movieMapper;

    @Inject
    ObjectMapper mapper;

    @ConfigProperty(name = "elasticsearch.indexes.movies")
    String moviesIndex;

    @Override
    public String saveMovie(Movie movie) {
        try {
            String movieAsJsonString = mapper.writeValueAsString(movie);
            IndexRequest indexRequest = new IndexRequest(moviesIndex).source(movieAsJsonString, XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            String id = indexResponse.getId();
            log.info("Document for '{}' {} successfully in ES. The id is: {}", movie, indexResponse.getResult(), id);
            return id;
        } catch (Exception e) {
            String errorMessage = String.format("An exception occurred while indexing '%s'. %s", movie, e.getMessage());
            log.error(errorMessage);
            throw new MovieServiceException(errorMessage, e);
        }
    }

    @Override
    public SearchMovieResponse searchMovies(String title) {
        try {
            SearchRequest searchRequest = new SearchRequest(moviesIndex);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("title", title));
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            log.info("Searching for '{}' took {} and found {}", title, searchResponse.getTook(), searchResponse.getHits().getTotalHits());
            return movieMapper.toSearchMovieResponse(searchResponse.getHits(), searchResponse.getTook());
        } catch (Exception e) {
            String errorMessage = String.format("An exception occurred while searching for title '%s'. %s", title, e.getMessage());
            log.error(errorMessage);
            return createSearchMovieResponseError(errorMessage);
        }
    }

    private SearchMovieResponse createSearchMovieResponseError(String errorMessage) {
        SearchMovieResponse searchMovieResponse = new SearchMovieResponse();
        searchMovieResponse.setError(new SearchMovieResponse.Error(errorMessage));
        return searchMovieResponse;
    }
}
