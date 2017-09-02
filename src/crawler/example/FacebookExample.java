package crawler.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


/**
 * 資料探索練習 Facebook Graph Api Search 
 * 
 * 重點
 * 1. 利用Graph Api調整出需要的資料
 * 2. 取得一組Access Token (試著使用 long term token)
 * 3. 試著用『excel』或任何最簡易的方式，對資料進行探索
 * 
 * @author Abola Lee
 *
 */
public class FacebookExample {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
		// [query sample]
		// search?fields=name,id,likes,talking_about_count&limit=100&q=e;鋼彈&type=page
		String uri = 
				"https://graph.facebook.com/v2.10"
				+ "/search?q=%E9%8B%BC%E5%BD%88&type=page&limit=1000&fields=name,id,likes,talking_about_count"
				+ "&access_token=EAACEdEose0cBACvZBWU252GxjTRD2pZASgEVRJFzhxg6Bi1sDdJ8I1mW9xCOpfWZAz2IPUkEgnjN8Hv82NhcXZChGyOOP58QW1bKhzjZCOZAwgNpRrO4lMJ7JGIUsvP9rRZCMtwCBPAZBy2tOWh9VF5kiIF2JO9NE7WIs5g8KEKWyTs2V7YUBFYqghqs7enDZCtoZD";



		// Jsoup select 後回傳的是  Elements 物件
//		[data sample]
//		----
//		{
//			"data": [
//			{
//				"name": "靠北工程師",
//					"id": "1632027893700148",
//					"likes": 174587,
//					"talking_about_count": 188119
//			}
//		}
		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,名稱,按讚數,討論人數\n";
		
		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String name = data.select("name").text();
			String likes = data.select("likes").text();
			String talking_about_count = data.select("talking_about_count").text();
			
			output += id+",\""+name+"\","+likes+","+talking_about_count+"\n";
		}
		
		System.out.println( output );
	} 
}
