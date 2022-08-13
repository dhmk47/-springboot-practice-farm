//package com.springboot.farm.springbootpractice.config;
//
//import java.util.Objects;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import net.sf.ehcache.Cache;
//
//@EnableAutoConfiguration(exclude = ElastiCacheAutoConfiguration.class)
//@Configuration
//@EnableCaching
//public class CacheConfig {
//	
//	@Bean
//	public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
//		return new EhCacheManagerFactoryBean();
//	}
//	
//	@Bean
//	public EhCacheCacheManager ehCacheCacheManager() {
//		
//		net.sf.ehcache.config.CacheConfiguration layoutCacheConfiguration  = new net.sf.ehcache.config.CacheConfiguration()
//				.eternal(false)						// true일 경우 timeout 관련 설정이 무시, element가 캐시에서 삭제되지 않음.
//				.timeToIdleSeconds(0)				// Element가 지정한 시간 동안 사용(조회)되지 않으면 캐시에서 제거된다. 이 값이 0인 경우 조회 관련 만료 시간을 지정하지 않는다.
//				.timeToLiveSeconds(0)				// Element가 존재하는 시간. 이 시간이 지나면 캐시에서 제거된다. 이 시간이 0이면 만료 시간을 지정하지 않는다.
//				.maxEntriesLocalHeap(0)				// Heap 캐시 메모리 pool size 설정, GC대상이 됨.
//				.memoryStoreEvictionPolicy("LRU")	// 캐시가 가득찼을때 관리 알고리즘 설정 default "LRU"
//				.name("layoutCaching");				// 캐시명
//		
//		// 설정을 가지고 캐시 생성
//		
//		Cache layoutCache = new net.sf.ehcache.Cache(layoutCacheConfiguration);
//		
//		// 캐시 팩토리에 생성한 eh캐시를 추가
//		Objects.requireNonNull(cacheManagerFactoryBean().getObject()).addCache(layoutCache);
//		
//		// 캐시 팩토리를 넘겨서 eh캐시 매니저 생성
//		return new EhCacheCacheManager(Objects.requireNonNull(cacheManagerFactoryBean().getObject()));
//		
//	}
//
//}