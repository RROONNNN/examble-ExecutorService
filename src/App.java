
import java.io.File;

import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class App {
        public final static int THREAD_COUNT = 4;
   public static void main(String[] args) throws IOException {
    args=new String[2];
    args[0]="aaaaa.txt";
        args[1]="ccccc.txt";
  

ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
for(String filename:args){
    File f=new File(filename);
    if (f.exists()){
        if(f.isDirectory()){
            File[] files=f.listFiles();
            for(int i=0;i<files.length;i++){
                if (!files[i].isDirectory()){
                    Runnable task=new GZipRunnable(files[i]);
                    pool.submit(task);
                }
            }
        }
        else {
            Runnable task=new GZipRunnable(f);
            pool.submit(task);
        }
    }
}
pool.shutdown();
}
  }
  