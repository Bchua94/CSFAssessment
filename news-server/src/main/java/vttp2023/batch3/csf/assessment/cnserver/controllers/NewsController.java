package vttp2023.batch3.csf.assessment.cnserver.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.NewsRequest;
import vttp2023.batch3.csf.assessment.cnserver.services.NewsService;

import java.io.IOException;


@RestController
@CrossOrigin
public class NewsController {

    @Autowired
    NewsService newsService;

	// TODO: Task 1

    @PostMapping("/news")
    public ResponseEntity<?> postNews(@ModelAttribute NewsRequest news) throws IOException {
        try{
            News news1= new News();
            news1.setDescription(news.getDescription());
            news1.setPostDate(news.getPostDate());
            news1.setTitle(news.getTitle());
            news1.setImage("https://loremflickr.com/320/240");
            news1.setTags(news.getTags());
            return ResponseEntity.ok(newsService.postNews(news1,news.getImage()));
        }
        catch(Exception e){
            throw e;
            // return ResponseEntity.badRequest().body(e.getMessage());
        }

    }




	// TODO: Task 2
    @GetMapping("/news")
    public ResponseEntity<?> getTagsByDuration(@PathParam("duration") Long duration) {
        try{
            return ResponseEntity.ok(newsService.getTags(duration));
        }
        catch(Exception e){
            throw e;
        }

    }

	// TODO: Task 3
    @GetMapping("/getAll")
    public ResponseEntity<?> getNewsByTag(@PathParam("duration") Long duration,@PathParam("tag") String tag) {
        try{
            return ResponseEntity.ok(newsService.getNewsByTag(duration, tag));
        }
        catch(Exception e){
            throw e;
        }

    }

}
