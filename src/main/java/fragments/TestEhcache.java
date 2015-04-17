package fragments;

import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
/**
 * 对MemoryStore，evict操作的主要流程是根据配置的EvictPolicy选取下一个expired或要被evict的Element，将这个Element移除，并出发expired或evict事件，在做evict之前先判断该Element或当前Store处于pinned状态，如果是，则不做evict，返回false。因而这里最主要的是要如何使用EvictPolicy选取下一个要被Evict的Element。EHCache实现了四种算法：最近最少使用算法（LRU）、先进先出算法（FIFO）、最少使用算法（LFU）、钟算法（CLOCK）。
 * 钟算法实现比较简单，它随即的选择一个Segment，每个Segment内部保存一个evictionIterator，每一次evict调用就是从这个Iterator中获取下一个expired Element或unpinned Element（如果该Iterator遍历到最后一个Element，则重新开始，即像钟一样不同的循环），将找到的Element从该Segment中移除。
 * 对其他的算法，都要先从MemoryStore中选取一个Element的样本数组，然后使用不同的Policy实现获取样本中的候选evict Element。样本Element数组的最大容量是30，其选取算法是：如果当前evict是因为新添加一个Element引起，则从新添加的Element所在的Segment中选取样本，否则随机的选取一个Segment，在选取的Segment中随机的选取一个HashEntry链，将这个链中所有unpinned Element加入的样本数据中，如果一条链不够，则循环的查找下一条链直到样本量达到指定的要求或整个Segment所有unpinned Element都已经添加到样本中。所有的算法都是基于这些样本选择下一个候选的evict Element。
 * FifoPolicy：样本中Update（Create）时间最早的Element。
 * LfuPolicy：样本中最少被使用的Element（Hit Count最少）。
 * LruPolicy：样本中最近最少被使用的Element（LastAccessTime最小）。
 *
 */
public class TestEhcache {

	public static void main(String[] args) {
		CacheManager singletonManager = CacheManager.create();
		//in-memory cache
		Cache memoryOnlyCache = new Cache("cache1", 5000, false, false, 5, 2);
		singletonManager.addCache(memoryOnlyCache);
		//overflow to disk cache
		int maxEntriesLocalHeap = 10000;
		Cache overflowToDiskCache = new Cache(
				  new CacheConfiguration("cache2", maxEntriesLocalHeap)
				    .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
				    .eternal(false)
				    .timeToLiveSeconds(60)
				    .timeToIdleSeconds(30)
				    .diskExpiryThreadIntervalSeconds(0)
				    .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP)));
		singletonManager.addCache(overflowToDiskCache);
		//using cache
		Cache cache1 = singletonManager.getCache("cache1");
		cache1.put(new Element("key1","value1"));
		System.out.println(cache1.get("key1").getObjectValue());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(cache1.get("key1")==null);
		//close cache manager
		singletonManager.shutdown();
	}

}
