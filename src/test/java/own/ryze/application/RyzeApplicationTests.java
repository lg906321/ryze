package own.ryze.application;

import org.junit.Test;

import own.ryze.application.weixin.util.FileReaderUtil;
import own.ryze.application.weixin.util.FileWriterUtil;

public class RyzeApplicationTests
{
	@Test
	public void test() throws Exception
	{
		boolean write = FileWriterUtil.write("D://test.txt", writer -> writer.write("辣子鸡"));
		System.out.println(write);
		int read = FileReaderUtil.read("D://test.txt", reader -> reader.read(new char[1024]));
		System.out.println(read);
	}

}
