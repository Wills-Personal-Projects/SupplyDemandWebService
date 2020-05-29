package com.SupplyDemand;

import java.util.ArrayList;

public class SupplyDemandUtil {
    private static ArrayList<Retailer> retailers;
    private static ArrayList<Producer> producers;
    private EventBroker brokerRef;

    public SupplyDemandUtil(){
        this.retailers = new ArrayList<>();
        this.producers = new ArrayList<>();
        brokerRef = EventBroker.getBroker();
    }

    public Retailer findRet(String retName){
        for (int i = 0; i < retailers.size(); i++) {
            if (retailers.get(i).getName().equals(retName)) {
                return retailers.get(i);
            }
        }
        return new Retailer(retName);
    }

    private Boolean retExist(String retName){
        for (int i = 0; i < retailers.size(); i++){
            if (retailers.get(i).getName().equals(retName)){
                return true;
            }
        }
        return false;
    }

    private Boolean prodExist(String prodName){
        for (int i = 0; i < producers.size(); i++){
            if (producers.get(i).getName().equals(prodName)){
                return true;
            }
        }
        return false;
    }

    private Producer findProd(String prodName){
        Producer found = null;
        for (int i = 0; i < producers.size(); i++) {
            if (producers.get(i).getName().equals(prodName)) {
                found = producers.get(i);
            }
        }
        return found;
    }

    public void publish(String name, String cat, String brand) {
        if (!prodExist(name))
            producers.add(new Producer(name));
        findProd(name).publish(cat, brand);
    }

    public void subscribe(String name, String cat) {
        if (!retExist(name))
            retailers.add(new Retailer(name));
        findRet(name).subscribe(cat);
    }

    public void unsubscribe(String name, String cat){
        if (retExist(name)) {
            findRet(name).unsubscribe(cat);
        }
    }

    public ArrayList<String> aggregateNotifs(){
        return brokerRef.getNotifications();
    }

    public void deleteNotifs(){

        brokerRef.reset();
    }

    public void utilReset(){
        retailers.clear();
        producers.clear();
        brokerRef.reset();
    }
}
