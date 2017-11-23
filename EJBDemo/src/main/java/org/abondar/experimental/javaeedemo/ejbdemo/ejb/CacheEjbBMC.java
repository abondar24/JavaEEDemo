package org.abondar.experimental.javaeedemo.ejbdemo.ejb;

import javax.ejb.*;
import java.util.HashMap;
import java.util.Map;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CacheEjbBMC {

    private Map<Long,Object> cache = new HashMap<>();

    public synchronized void addToCache(Long id,Object object){
        if (!cache.containsKey(id)){
            cache.put(id, object);
        }
    }

    public synchronized void removeFromCache(Long id){
        if (cache.containsKey(id)){
            cache.remove(id);
        }
    }


    public Object getFromCache(Long id){
        return cache.getOrDefault(id, null);
    }

    public Integer getNumberOfItems(){
        return cache.size();
    }
}
