package own.ryze.application;

import org.junit.Test;

import own.ryze.application.weixin.common.PortReturn;
import own.ryze.application.weixin.enums.Return;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RyzeApplication.class)
@WebAppConfiguration*/
public class RyzeApplicationTests
{
	@Test
	public void contextLoads()
	{
	}
	
	@Test
	public void testMap()
	{
		PortReturn.put("data", "test");
		PortReturn.put(Return.SUCCESS);
		
		System.out.println(PortReturn.returnJSON());
	}

}
