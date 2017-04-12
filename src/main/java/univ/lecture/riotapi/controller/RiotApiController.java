package univ.lecture.riotapi.controller;

import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
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
import springfox.documentation.spring.web.json.Json;
import univ.lecture.riotapi.Calculator;
import univ.lecture.riotapi.model.Team10;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;


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

    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Team10 queryTeam10(@RequestParam(value="exp")String exp){

    	Calculator cal = new Calculator();
    	double result = cal.calculate(cal.postfix(exp));
    	long now = System.currentTimeMillis();
    	Team10 team10 = new Team10(10,now,result);
    	log.debug("interrupt");

    	return team10;
    }

    

//    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public @ResponseBody Map<String, Object> getJsonByMap() {
//    	Map<String, Object> jsonObject = new HashMap<String, Object>();
//    	Map<String, Object> jsonSubObject = null;
//    	ArrayList<Map<String,Object>> jsonList = new ArrayList<Map<String,Object>>();
//
//    	jsonSubObject = new HashMap<String, Object>();
//        jsonSubObject.put("idx", 1);
//        jsonSubObject.put("title", "제목입니다");
//        jsonSubObject.put("create_date", new Date());
//        jsonList.add(jsonSubObject);
//        //2번째 데이터
//        jsonSubObject = new HashMap<String, Object>();
//        jsonSubObject.put("idx", 2);
//        jsonSubObject.put("title", "두번째제목입니다");
//        jsonSubObject.put("create_date", new Date());
//        jsonList.add(jsonSubObject);
//
//        jsonObject.put("success", true);
//        jsonObject.put("total_count", 10);
//        jsonObject.put("result_list", jsonList);
//
//        return jsonObject;
//    }
}
