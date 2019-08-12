 package net.copaamerica.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.copaamerica.model.News;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
	public String searchingImage(String data) {
		String resp = "";
		Pattern logEntry = Pattern.compile("\\((.*?)\\)");
        java.util.regex.Matcher matchPattern = logEntry.matcher(data);

        while(matchPattern.find()) {
            resp = resp + matchPattern.group(1);
        }
        
        return resp;
	}

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
    @GetMapping("/news")
    public String getNews(Model model) {
    	
    	List<News> listNews = new ArrayList<>();
		Document document = null;
		//connection
		try {
			document = Jsoup.connect("https://copaamerica.com/es/noticias/").get();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("We cannot connet to url : https://copaamerica.com/es/noticias");
			model.addAttribute("errorConection","Error al conectar con la fuente: https://copaamerica.com/es/noticias/");
		}
    	        
    
		News n = null;
		if(document != null){
			for(Element row: document.select(".ac-news-cards .ac-card-links")) {
				n = new News();
	    		//title
	    		final String content = row.select(".ac-card-card-title span").text();
	    		n.setContent(content);
	    		
	    		n.setTitle(content.substring(0,10) + "...");
	    		//rating
	    		final Element elementImage = row.select(".ac-card-card-top").first();
	    		final String image = elementImage.attr("style");
	    		
	    		Element link = row.select(".ac-card-links").first();         
	    		//System.out.println("Relative Link: " + link.attr("href"));
	    		n.setUrl("https://copaamerica.com" + link.attr("href"));
	    		
	    		//System.out.println(searchingImage(image));
	    		n.setImage(searchingImage(image));
	    		
	    		listNews.add(n);
	    		//System.out.println("News title : " + title + " Image : " + image);
	    	}
			model.addAttribute("list", listNews);
		}
        return "news";
    }
    
}
