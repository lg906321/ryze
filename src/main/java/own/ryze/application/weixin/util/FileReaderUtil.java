package own.ryze.application.weixin.util;

import java.io.FileReader;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import own.ryze.application.weixin.functioninterface.File;

@Slf4j
public class FileReaderUtil implements AutoCloseable
{
	private final FileReader reader;

	private FileReaderUtil(String filename) throws IOException
	{
		reader = new FileReader(filename);
		log.info("open filereader");
	}

	@Override
	public void close() throws IOException
	{
		reader.close();
		log.info("close filereader");
	}

	public static int read(final String filename, final File<FileReaderUtil, Integer, IOException> reader) throws IOException
	{
		try (final FileReaderUtil fileReader = new FileReaderUtil(filename))
		{
			return reader.accept(fileReader);
		}
	}

	public int read() throws IOException
	{
		return reader.read();
	}

	public int read(final char[] cbuf) throws IOException
	{
		return reader.read(cbuf);
	}

	public int read(final char[] cbuf, final int offset, final int length) throws IOException
	{
		return reader.read(cbuf, offset, length);
	}
}
