package own.ryze.application;

import org.junit.Test;

import own.ryze.application.weixin.util.StringUtil;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RyzeApplication.class)
@WebAppConfiguration*/
public class RyzeApplicationTests
{
	private static final String MOBILE = "^(1[3,5,8,7]{1}[\\d]{9})|(((400)-(\\d{3})-(\\d{4}))|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)$";
	
	@Test
	public void contextLoads()
	{
	}
	
	@Test
	public void tessttt()
	{
		String value = "";
		boolean b =  (StringUtil.isNotEmpty(value) && value.matches(MOBILE));
		System.out.println(b);
	}

}
