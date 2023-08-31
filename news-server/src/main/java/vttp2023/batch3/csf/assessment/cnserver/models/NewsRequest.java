package vttp2023.batch3.csf.assessment.cnserver.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/* Do not modify this file */


@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {

    @Id
    private String id;
    private long postDate;
    private String title;
    private String description;
    private MultipartFile image;
    private List<String> tags;

    public void setId(String id) { this.id = id; }
    public String getId() { return this.id; }

    public void setPostDate(long postDate) { this.postDate = postDate; }
    public long getPostDate() { return this.postDate; }
    public Date getPostDateAsDate() { return new Date(this.postDate); }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return this.title; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return this.description; }

    public void setImage(MultipartFile image) { this.image = image; }
    public MultipartFile getImage() { return this.image; }

    public void setTags(List<String> tags) { this.tags = tags; }
    public List<String> getTags() { return this.tags; }

    @Override
    public String toString() {
        return "News{id: %s, postDate: %d, title: %s}".formatted(id, getPostDateAsDate(), title);
    }

    /* Do not modify this file */
}
