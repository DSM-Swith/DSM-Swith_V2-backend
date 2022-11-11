package com.swith.backend.global.aws;

//import java.io.IOException;
//import java.util.UUID;
//
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.swith.backend.global.exception.BadRequestException;
//import com.swith.backend.global.exception.ImageBadRequestException;
//import com.swith.backend.global.exception.ImageNotSaveException;
//
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class S3Util {
//
//	private final AmazonS3 amazonS3;
//
	@Value("${cloud.aws.s3.url}")
	private String s3BaseUrl;
//
//	@Value("${cloud.aws.s3.bucket}")
//	private String bucketName;
//
//	public void delete(String objectName) {
//		amazonS3.deleteObject(bucketName, objectName);
//	}
//
//	public String uploadImage(String fileUrl, MultipartFile file) {
//		String extension = verificationFile(file);
//		String filePath;
//		try{
//			filePath = saveImage(fileUrl, file, extension);
//		} catch (IOException e) {
//			throw ImageNotSaveException.EXCEPTION;
//		}
//		return filePath;
//	}
//
	public String getS3ObjectUrl(String path) {
		return s3BaseUrl + path;
	}
//
//	private String verificationFile(MultipartFile file) {
//		if(file == null || file.isEmpty() || file.getOriginalFilename() == null) throw ImageBadRequestException.EXCEPTION;
//
//		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//		if (!(extension.contains(".JPG") || extension.contains(".jpg") || extension.contains(".jpeg") || extension.contains(".JPEG") || extension.contains(".png") || extension.contains(".PNG"))) {
//			throw ImageBadRequestException.EXCEPTION;
//		}
//
//		return extension;
//	}
//
//	private String saveImage(String fileUrl, MultipartFile file, String extension) throws IOException {
//		String filePath = fileUrl + "/" + UUID.randomUUID() + extension;
//
//		amazonS3.putObject(new PutObjectRequest(bucketName, filePath, file.getInputStream(), null)
//			.withCannedAcl(CannedAccessControlList.PublicRead));
//		return filePath;
//	}


}
