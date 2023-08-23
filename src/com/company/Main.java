package com.company;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;

public class Main {

    private HttpClient httpClient;

    public static void main(String[] args)  {
       Main main = new Main();
       String hod = main.Start();
    }

    public int[][] labyrinth = {
            {2, 2, 2, 2, 2},
            {2, 1, 1, 1, 2},
            {2, 1, 2, 1, 2},
            {2, 1, 2, 1, 2},
            {2, 0, 2, 8, 2}
    };

    public int[] search(int[][] labyrinthforsearch){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(labyrinthforsearch[i][j] == 8){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    public void VivodMass(){
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                System.out.print(labyrinth[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String Start(){


        try{
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost",8090),0);
            httpServer.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    //HttpContext context = exchange.getHttpContext();
                    InputStream inputStream = exchange.getRequestBody();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                    }
                    rd.close();
                    //System.out.println(response.toString());
                    //Main main = new Main();
                    switch(response.toString()){
                        case "w":
                            int[] resultsearch = search(labyrinth);

                            if(labyrinth[resultsearch[0]-1][resultsearch[1]] == 2){
                                System.out.println("Впереди стена");
                            }
                            else if(labyrinth[resultsearch[0]-1][resultsearch[1]] == 1) {
                                labyrinth[resultsearch[0]][resultsearch[1]] = 1;
                                labyrinth[resultsearch[0] - 1][resultsearch[1]] = 8;

                                VivodMass();
                            }
                            else if(labyrinth[resultsearch[0]-1][resultsearch[1]] == 0){
                                labyrinth[resultsearch[0]][resultsearch[1]] = 1;
                                labyrinth[resultsearch[0] - 1][resultsearch[1]] = 8;

                                VivodMass();

                                System.out.println();
                                System.out.println("Вы дошли до финиша");
                            }
                        break;
                        case "a":
                            int[] resultsearch1 = search(labyrinth);

                            if(labyrinth[resultsearch1[0]][resultsearch1[1]-1] == 2){
                                System.out.println("слева стена");
                            }
                            else if(labyrinth[resultsearch1[0]][resultsearch1[1]-1] == 1) {
                                labyrinth[resultsearch1[0]][resultsearch1[1]] = 1;
                                labyrinth[resultsearch1[0]][resultsearch1[1]-1] = 8;

                                VivodMass();
                            }
                            else if(labyrinth[resultsearch1[0]][resultsearch1[1]-1] == 0){
                                labyrinth[resultsearch1[0]][resultsearch1[1]] = 1;
                                labyrinth[resultsearch1[0]][resultsearch1[1]-1] = 8;

                                VivodMass();

                                System.out.println();
                                System.out.println("Вы дошли до финиша");
                            }
                            break;
                        case "d":
                            int[] resultsearch2 = search(labyrinth);

                            if(labyrinth[resultsearch2[0]][resultsearch2[1]+1] == 2){
                                System.out.println("справа стена");
                            }
                            else if(labyrinth[resultsearch2[0]][resultsearch2[1]+1] == 1) {
                                labyrinth[resultsearch2[0]][resultsearch2[1]] = 1;
                                labyrinth[resultsearch2[0]][resultsearch2[1]+1] = 8;

                                VivodMass();
                            }
                            else if(labyrinth[resultsearch2[0]][resultsearch2[1]+1] == 0){
                                labyrinth[resultsearch2[0]][resultsearch2[1]] = 1;
                                labyrinth[resultsearch2[0]][resultsearch2[1]+1] = 8;

                                VivodMass();

                                System.out.println();
                                System.out.println("Вы дошли до финиша");
                            }
                            break;
                        case "s":
                            int[] resultsearch4 = search(labyrinth);

                            if(labyrinth[resultsearch4[0]+1][resultsearch4[1]] == 2){
                                System.out.println("внизу стена");
                            }
                            else if(labyrinth[resultsearch4[0]+1][resultsearch4[1]] == 1) {
                                labyrinth[resultsearch4[0]][resultsearch4[1]] = 1;
                                labyrinth[resultsearch4[0]+1][resultsearch4[1]] = 8;

                                VivodMass();
                            }
                            else if(labyrinth[resultsearch4[0]+1][resultsearch4[1]] == 0){
                                labyrinth[resultsearch4[0]][resultsearch4[1]] = 1;
                                labyrinth[resultsearch4[0]+1][resultsearch4[1]] = 8;

                                VivodMass();

                                System.out.println();
                                System.out.println("Вы дошли до финиша");
                            }
                            break;

                        case "q":
                            System.out.println("Вы обозначены как цифра 8");

                            VivodMass();

                            break;
                    }
                }
            });
            httpServer.setExecutor(null);
            httpServer.start();
        }
        catch (IOException e)
        {
        }
        return new String();
    }
}





















