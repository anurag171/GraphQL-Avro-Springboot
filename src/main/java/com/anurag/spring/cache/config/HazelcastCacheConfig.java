package com.anurag.spring.cache.config;

import com.hazelcast.config.*;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HazelcastCacheConfig {

//    @Bean
//    public Config config(){
//
//        return new Config()
//                .setInstanceName("hazelcast-instance")
//                .addMapConfig(new MapConfig()
//                        .setName("employeeCache")
//                        .setTimeToLiveSeconds(300))
//                ;
//    }


//    @Bean
//    public Config config() {
//        final Config cfg = new Config();
//        cfg.getGroupConfig().setName("dev");
//        cfg.setManagementCenterConfig(manCenterCfg());
//        return cfg;
//    }
//
//    @Bean
//    public Map<integer, employee=""> employeeMap(final HazelcastInstance instance) {
//        return instance.getMap("employeeMap");
//    }
//
//    @Bean
//    public HazelcastInstance instance(final Config cfg) {
//        return Hazelcast.newHazelcastInstance(cfg);
//    }
//
//    private ManagementCenterConfig manCenterCfg() {
//        return new ManagementCenterConfig()
//                .setEnabled(true)
//                // port number should be same on which the hazelcast management center is running
//                .setUrl("http://localhost:10080/hazelcast-mancenter");
//    }
}
