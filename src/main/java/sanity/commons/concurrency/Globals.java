package sanity.commons.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Globals {

	public static final int cores = Runtime.getRuntime().availableProcessors();
	public static final ExecutorService pool = Executors.newFixedThreadPool(Globals.cores);

}
