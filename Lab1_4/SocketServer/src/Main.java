import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

/*
 * Лабораторная 1.4
 * Переделать SocketServer в многопоточный вариант,
 * чтобы он мог надёжно обработать 100 запросов одновременно,
 * используя пулы потоков.
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// 127.0.0.1:1111
		// localhost:1111
		
		try (ServerSocket ss = new ServerSocket(1111)) {
			AtomicInteger req = new AtomicInteger(); // AtomicInteger
			// create pool
			ExecutorService es = Executors.newCachedThreadPool();

			
			while (true)
			{
				Socket cs = ss.accept();
				
				Runnable runnable = () -> {
					out.printf("Accept connection from %s\n",
							cs.getInetAddress().toString());
					
					try (BufferedReader reader = new BufferedReader( 
						new InputStreamReader(cs.getInputStream(), 
							Charset.forName("UTF-8")))) {
						String s = "";
						try {
							s = reader.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						out.printf("%s . %d\n",s,req.incrementAndGet());
						Thread.sleep(100);
						
						OutputStreamWriter writer = new OutputStreamWriter(
								cs.getOutputStream(),
								Charset.forName("UTF-8"));
						
						writer.write(s.toUpperCase()+"\n");
						writer.flush();
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
				};

				es.submit(runnable);
			}
		}

	}

}
