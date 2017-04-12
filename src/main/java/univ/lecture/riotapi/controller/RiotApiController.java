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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.model.Summoner;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by tchi on 2017. 4. 1..
 */
/*
 * 컨트롤러를 구현 == 클라이언트의 요청을 처리할 메서드를 구현
 * 클라이언트는 URL로 요청을 전송
 * 요청 URL을 어떤 메서드가 처리할지 여부를 결정하는 것이 @RequestMapping
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

    @RequestMapping(value = "/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Summoner querySummoner(@RequestBody@PathVariable("name") String summonerName) throws UnsupportedEncodingException {
        final String url = riotApiEndpoint + summonerName;
//        		+ "/summoner/by-name/" +
//                summonerName +
//                "?api_key=" +
//                riotApiKey;

        String response = restTemplate.getForObject(url, String.class);
        Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(response);

        parsedMap.forEach((key, value) -> log.info(String.format("key [%s] type [%s] value [%s]", key, value.getClass(), value)));

        Map<String, Object> summonerDetail = (Map<String, Object>) parsedMap.values().toArray()[0];
        String queriedName = (String)summonerDetail.get("name");
        int queriedLevel = (Integer)summonerDetail.get("summonerLevel");
        Summoner summoner = new Summoner(queriedName, queriedLevel);

        return summoner;
    }
}
