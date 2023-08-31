package vttp2023.batch3.csf.assessment.cnserver.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

import java.io.IOException;
import java.util.List;

@Service
public class NewsService {


	@Value("${s3.bucket.name}")
	private String doSpaceBucket;

	@Autowired
	NewsRepository newsRepository;

	String FOLDER = "files/";

	@Autowired
	AmazonS3 s3Client;
	
	// TODO: Task 1
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns the news id
	public String postNews(News news,MultipartFile multipartFile) throws IOException {
//		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//		String imgName = FilenameUtils.removeExtension(multipartFile.getOriginalFilename());
//		String key = FOLDER + imgName + "." + extension;
//		PutObjectResult putObjectResult=  saveImageToServer(multipartFile, key);
//		news.setImage(putObjectResult.getMetadata().getVersionId());
		News newsSaved = newsRepository.save(news);
		return newsSaved.getId();
	}

	private PutObjectResult saveImageToServer(MultipartFile multipartFile, String key) throws IOException {

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(multipartFile.getInputStream().available());
		if (multipartFile.getContentType() != null && !"".equals(multipartFile.getContentType())) {
			metadata.setContentType(multipartFile.getContentType());
		}
		return s3Client.putObject(new PutObjectRequest(doSpaceBucket, key, multipartFile.getInputStream(), metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
	}
	 
	// TODO: Task 2
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of tags and their associated count
	public List<?> getTags(Long duration) {
		List<?> tagCountProjectionList = newsRepository.findTagsByTime(duration);
		return tagCountProjectionList;
	}

	// TODO: Task 3
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of news
	public List<News> getNewsByTag(long duration,String tag) {
		return  newsRepository.findAllByTagsContainsAndPostDateGreaterThanOrderByPostDateDesc(tag,duration);

	}
	
}
