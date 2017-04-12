package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.Calculator;
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

    @RequestMapping(method = RequestMethod.POST)
    public String queryCal(@RequestParam("exp") String exp) throws UnsupportedEncodingException {
        final int teamId = 10;
        long now = System.currentTimeMillis();
        double result;
        
        Calculator calculator = new Calculator();
        result = calculator.calculate(exp);
        
        Map<String, Object> cal = new HashMap<String, Object>();
        cal.put("teamId", teamId);
        cal.put("now", now);
        cal.put("result", result);
        
        String msg = restTemplate.postForObject(riotApiEndpoint, cal, String.class);

        return msg;
    }
}
