package own.ryze.application.weixin.util;

import java.io.FileWriter;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import own.ryze.application.weixin.functioninterface.File;

/**
 * 文件读取工具
 * 
 * @author LCY
 *
 */
@Slf4j
public class FileWriterUtil implements AutoCloseable
{
	private final FileWriter writer;

	private FileWriterUtil(final String filename) throws IOException
	{
		writer = new FileWriter(filename);
		log.info("open filewriter");
	}

	@Override
	public void close() throws IOException
	{
		writer.close();
		log.info("close filewriter");
	}

	public static boolean write(final String filename, final File<FileWriterUtil,Boolean,IOException> writer) throws IOException 
	{
		try (final FileWriterUtil fileWriter = new FileWriterUtil(filename))
		{
			return writer.accept(fileWriter);
		}
	}

	public boolean write(final String str) throws IOException
	{
		writer.write(str);
		return StringUtil.isNotEmpty.test(str);
	}

	public boolean write(final int i) throws IOException
	{
		writer.write(i);
		return i == 0 ? false:true;
	}

	public boolean write(final char[] cbuf) throws IOException
	{
		writer.write(cbuf);
		return cbuf == null || cbuf.length == 0? false:true;
	}

	public boolean write(final char[] cbuf, final int off, final int len) throws IOException
	{
		writer.write(cbuf, off, len);
		return cbuf == null || cbuf.length == 0? false:true;
	}

	public boolean write(final String str, final int off, final int len) throws IOException
	{
		writer.write(str, off, len);
		return StringUtil.isNotEmpty.test(str);
	}

}