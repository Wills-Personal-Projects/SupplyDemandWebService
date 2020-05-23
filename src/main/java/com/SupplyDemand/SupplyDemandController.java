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

    @PostMapping("/publish")
    public void publish(@RequestBody PubAction p){
        util.publish(p.getName(), p.getCat(), p.getBrand());
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody SubAction s){
        util.subscribe(s.getName(), s.getCat());
    }

    @DeleteMapping("/unsubscribe")
    public void subscribe(@RequestBody UnSubAction us){
        util.unsubscribe(us.getName(), us.getCat());
    }

    @GetMapping("/notifications")
    public List<String> notifications(){
        return util.aggregateNotifs();
    }
}
