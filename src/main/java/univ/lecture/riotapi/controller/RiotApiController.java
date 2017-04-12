package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.Calculator;
import univ.lecture.riotapi.model.Team10;
import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1/calc")
@Log4j
public class RiotApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${riot.api.endpoint}")
    private String riotApiEndpoint;

    @Value("${riot.api.key}")
    private String riotApiKey;

//    @RequestMapping(value = "/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public @ResponseBody Summoner querySummoner(@RequestBody @PathVariable("name") String summonerName) throws UnsupportedEncodingException {//ResposeBody를 사용하여 해당 메소드의 리턴값을 http응답 데이터로 사용
//        final String url = riotApiEndpoint + "/summoner/by-name/" +
//                summonerName +
//                "?api_key=" +
//                riotApiKey;
//
//        String response = restTemplate.getForObject(url, String.class);
//        Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(response);
//
//        parsedMap.forEach((key, value) -> log.info(String.format("key [%s] type [%s] value [%s]", key, value.getClass(), value)));
//
//        Map<String, Object> summonerDetail = (Map<String, Object>) parsedMap.values().toArray()[0];
//        String queriedName = (String)summonerDetail.get("name");
//        int queriedLevel = (Integer)summonerDetail.get("summonerLevel");
//        
////        Calculator cal = new Calculator();
////        double result = cal.calculate(summonerName);
//        Summoner summoner = new Summoner(queriedName, queriedLevel);
//        
//        
//        return summoner;
//    }
    	
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Team10 queryTeam10(@RequestParam(value="exp")String exp){
    	
    	Calculator cal = new Calculator();
    	long now = System.currentTimeMillis();
    	double result = cal.calculate(exp);
    	Team10 team10 = new Team10(10,now,result);
    	
    	return team10;
    }
}