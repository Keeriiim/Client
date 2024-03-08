package httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;


public class Backend {
    HttpClient httpClient;

    public Backend(){
        this.httpClient = new CloseableHttpClient() {
        };
        start();
    }

    public void start() {
        Scanner scan = new Scanner(System.in);

        boolean online = true;
        while (online == true) {

            System.out.println("1. Get method " + "\n2. Post method" + "\n3. Delete method" + "\n4. Put method" + "\n5.Exit");
            String input = scan.nextLine();
            System.out.println(input);

            switch (input) {
                case "1":
                    getRequest(httpClient);
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    online = false;
                    break;
                default:
                    System.out.println("Provide a number between 1-5");


            }
        }
    }

    public void getRequest(HttpClient httpClient){
        this.httpClient = httpClient;
        URI uri = URI.create("http://beanstalk-spring-crud-env.eba-diuswvnf.eu-north-1.elasticbeanstalk.com/sqlbooks");

        HttpUriRequest request = (HttpUriRequest) newHttpClient();
        try {
            HttpResponse response = httpClient.execute(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();



    }


}
}
