package org.abondar.experimental.javaeedemo.ejbdemo.ejb;


import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Singleton
@Lock(LockType.WRITE)
@AccessTimeout(value = 20,unit = TimeUnit.SECONDS)
public class CacheEJB {

    private Map<Long,Object> cache = new HashMap<>();


    @PostConstruct
    private void initCache(){
        cache.put(1L,"First item in the cache");
        cache.put(2L,"Second item in the cache");
    }

    @AccessTimeout(0)
    public void addToCache(Long id,Object object){
        if (!cache.containsKey(id)){
            cache.put(id, object);
        }
    }

    public void removeFromCache(Long id){
        if (cache.containsKey(id)){
            cache.remove(id);
        }
    }

    @Lock(LockType.READ)
    public Object getFromCache(Long id){
        return cache.getOrDefault(id, null);
    }

    public Integer getNumberOfItems(){
        return cache.size();
    }
}
