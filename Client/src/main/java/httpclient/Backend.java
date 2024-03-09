package httpclient;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Backend {

    private HttpClient httpClient;
    private String base_url = "http://beanstalk-spring-crud-env.eba-diuswvnf.eu-north-1.elasticbeanstalk.com/";

    public Backend(){
        this.httpClient = HttpClient.newHttpClient();
        start();
    }

    public void start() {
        Scanner scan = new Scanner(System.in);

        boolean online = true;
        while (online == true) {

            System.out.println("\n1. Get method " + "\n2. Post method" + "\n3. Delete method" + "\n4. Put method" + "\n5.Exit");
            System.out.print("Choose a number: ");
            String input = scan.nextLine();
            System.out.println();

            switch (input) {
                case "1":
                    try {
                        System.out.println(getRequest());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    System.out.println("Enter the title of the book: ");
                    String title = scan.nextLine();
                    System.out.println("Enter the author of the book: ");
                    String author = scan.nextLine();

                    System.out.println(title + " " + author);
                    try {
                        System.out.println(postRequest(title, author));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("Enter the id of the book you want to delete: ");
                    String id = scan.nextLine();
                    try {
                        System.out.println(deleteRequest(id));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    System.out.println("Enter the id of the book you want to update: ");
                    String id2 = scan.nextLine();
                    System.out.println("Enter the new title of the book: ");
                    String title2 = scan.nextLine();
                    System.out.println("Enter the new author of the book: ");
                    String author2 = scan.nextLine();
                    try {
                        updateRequest(id2, title2, author2);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    online = false;
                    break;
                default:
                    System.out.println("Provide a number between 1-5");


            }
        }
    }


    public String getRequest() throws IOException, InterruptedException {
        String get_url = this.base_url;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(get_url + "sqlbooks"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String postRequest(String title, String author) throws IOException, InterruptedException {
        String post_url = this.base_url;
        String jsonBody = "{\"title\": \"" +title + "\", \"author\": \"" + author+ "\"}";


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(post_url + "sqlbooks"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String deleteRequest(String id) throws IOException, InterruptedException {
        String delete_url = this.base_url;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(delete_url + "sqlbooks/" + id))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String updateRequest(String id2, String title2, String author2) throws IOException, InterruptedException {
        String delete_url = this.base_url;
        String jsonBody = "{\"title\": \"" +title2 + "\", \"author\": \"" + author2+ "\"}";


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(delete_url + "sqlbooks/" + id2))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }
}

