package com.SupplyDemand;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SupplyDemand")
public class SupplyDemandController {

    private SupplyDemandUtil util;

    public SupplyDemandController(){
        util = new SupplyDemandUtil();
    }

    @CrossOrigin(origins = {"http://supplydemandclient.s3-website-us-west-1.amazonaws.com","http://localhost:4200"})
    @PostMapping("/publish")
    public void publish(@RequestBody PubAction p){
        util.publish(p.getName(), p.getCat(), p.getBrand());
    }

    @CrossOrigin(origins = {"http://supplydemandclient.s3-website-us-west-1.amazonaws.com","http://localhost:4200"})
    @PostMapping("/subscribe")
    public void subscribe(@RequestBody SubAction s){
        util.subscribe(s.getName(), s.getCat());
    }

    @CrossOrigin(origins = {"http://supplydemandclient.s3-website-us-west-1.amazonaws.com","http://localhost:4200"})
    @PostMapping("/unsubscribe")
    public void unsubscribe(@RequestBody UnSubAction us){
        util.unsubscribe(us.getName(), us.getCat());
    }

    @CrossOrigin(origins = {"http://supplydemandclient.s3-website-us-west-1.amazonaws.com","http://localhost:4200"})
    @GetMapping("/notifications")
    public List<String> notifications(){
        return util.aggregateNotifs();
    }

    @CrossOrigin({"http://supplydemandclient.s3-website-us-west-1.amazonaws.com","http://localhost:4200"})
    @DeleteMapping("/deletenotifications")
    public void DeleteNotifications(){
        util.deleteNotifs();
    }
}
