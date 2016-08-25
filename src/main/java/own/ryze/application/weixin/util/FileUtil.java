package own.ryze.application.weixin.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具包
 * 
 * @author LCY
 *
 */
public class FileUtil
{
	private FileUtil()
	{
	}
	
	/**
	 * 上传
	 * 
	 * @param file
	 * @param userFilepath
	 * @return
	 */
	public static String upload(final MultipartFile file, final String userFilepath)
	{
		if (file == null || file.isEmpty()) return null;
		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		final String pathname = new StringBuilder(request.getScheme()).append("://").append(request.getServerName())
				.append(":").append(request.getServerPort()).append(request.getContextPath()).append(File.separator)
				.toString();

		final String originalFilename = file.getOriginalFilename();
		// 文件保存路径
		final String savePath = new StringBuilder(userFilepath).append("/")
				.append(Long.toString(System.currentTimeMillis())).append(".")
				.append(originalFilename.substring(originalFilename.lastIndexOf(".") + 1)).toString();

		final String filePath = new StringBuilder(request.getSession().getServletContext().getRealPath("/"))
				.append(savePath).toString();

		final File params = new File(filePath);
		// 生成所有目录
		if (!params.exists()) params.mkdirs();
		try
		{
			// 存文件
			file.transferTo(params);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// 保存文件路径
		final String fileurl = new StringBuilder(pathname).append(savePath).toString().replace("\\", "/");
		return fileurl;
	}
	
}
