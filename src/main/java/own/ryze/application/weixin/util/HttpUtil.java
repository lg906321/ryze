package own.ryze.application.weixin.util;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import own.ryze.application.weixin.constants.WeixinParameter;

/**
 * http工具包
 * 
 * @author LCY
 *
 */
public class HttpUtil
{

	private HttpUtil()
	{
	}

	/**
	 * 初始化
	 * 
	 * @param isHttps
	 *            是否https
	 * @return
	 * @throws Exception
	 */
	private static HttpClient init(final boolean isHttps) throws Exception
	{
		final HttpClient httpClient;
		// https 证书 密码存在
		if (isHttps && WeixinParameter.CERTPATH != null && WeixinParameter.CERTPASSWORD != null)
		{
			// pkcs12类型密钥
			final KeyStore keyStore = KeyStore.getInstance("PKCS12");
			// 读取本地证书
			final InputStream is = HttpUtil.class.getClassLoader().getResourceAsStream(WeixinParameter.CERTPATH);
			// 通过密码加载证书
			keyStore.load(is, WeixinParameter.CERTPASSWORD.toCharArray());
			// 通过证书构建SSL 只允许TLSv1协议
			final SSLContext sslContext = SSLContextBuilder.create().useProtocol("TLSv1")
					.loadKeyMaterial(keyStore, WeixinParameter.CERTPASSWORD.toCharArray()).build();

			is.close();
			// 只允许TLSv1协议
			final SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
			// 注册证书
			httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
		}
		// http
		else httpClient = HttpClients.custom().build();
		return httpClient;
	}

	private static String get(final String url, boolean isHttps)
	{
		try
		{
			final HttpClient httpClient = init(isHttps);
			final HttpGet get = new HttpGet(url);
			get.addHeader("Content-Type", "application/json,application/xml; charset=UTF-8");  
			get.setHeader("Accept", "application/json,application/xml;");
			get.addHeader("Accept-Charset", "UTF-8");
			final HttpResponse response = httpClient.execute(get);
			final HttpEntity entity = response.getEntity();
			final String result = EntityUtils.toString(entity, "UTF-8");
			get.abort();
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * http get
	 * 
	 * @param url
	 * @return
	 */
	public static String get(final String url)
	{
		return get(url, false);
	}

	/**
	 * https get
	 * 
	 * @param url
	 * @return
	 */
	public static String gets(final String url)
	{
		return get(url, true);
	}

	private static String post(final String url, final String data, final boolean isHttps)
	{
		try
		{
			final HttpClient httpClient = init(isHttps);

			final HttpPost post = new HttpPost(url);
			post.addHeader("Content-Type", "application/json,application/xml; charset=UTF-8");  
			post.setHeader("Accept", "application/json,application/xml;");
			post.addHeader("Accept-Charset", "UTF-8");
			final StringEntity stringEntity = new StringEntity(data, "UTF-8");
			post.setEntity(stringEntity);
			final HttpResponse response = httpClient.execute(post);
			final HttpEntity entity = response.getEntity();
			final String result = EntityUtils.toString(entity, "UTF-8");
			post.abort();
			return result;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * http post
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(final String url,final String data)
	{
		return post(url, data, false);
	}
	
	/**
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String posts(final String url,final String data)
	{
		return post(url, data, true);
	}
}
