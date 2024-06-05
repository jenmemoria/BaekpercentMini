package board;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;


public class FileUtil2 {
	private static FileUtil2 instance = new FileUtil2();
	
	public static FileUtil2 getInstance() {
		return instance;
	}
	private FileUtil2() {}
	
	private final String saveDirectory = "C:\\upload";
	private final int maxPostSize = 1024 * 1024 * 50;
	private final String encoding = "UTF-8";
	private final FileRenamePolicy policy = new DefaultFileRenamePolicy();
	
	public BoardDTO getDTO(HttpServletRequest request) throws IOException {
		BoardDTO dto = new BoardDTO();
		MultipartRequest mpRequest = new MultipartRequest(
				request, saveDirectory, maxPostSize, encoding, policy);
		
		File uploadFile = mpRequest.getFile("uploadFile");
		if(uploadFile != null) {
			
			dto.setImage(uploadFile.getName());
		}
		
		dto.setTitle(mpRequest.getParameter("title"));
		dto.setWriter(mpRequest.getParameter("writer"));
		dto.setContent(mpRequest.getParameter("content"));
		
		
		return dto;
	}
}
