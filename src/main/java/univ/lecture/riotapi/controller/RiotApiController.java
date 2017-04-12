package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.Calculator;
import univ.lecture.riotapi.model.ObjectTeam10;
import univ.lecture.riotapi.model.Team10;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
    public @ResponseBody Object queryTeam10(@RequestParam(value="exp")String exp){

    	Calculator cal = new Calculator();
    	double result = cal.calculate(cal.postfix(exp));
    	long now = System.currentTimeMillis();
    	Team10 team10 = new Team10(10,now,result);
    	ObjectTeam10 objectTeam10 = new ObjectTeam10();
    	
    	objectTeam10.setMsg("Recorded");
    	return objectTeam10;
    }
    
}
