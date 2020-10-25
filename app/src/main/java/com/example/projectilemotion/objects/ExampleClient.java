package com.example.projectilemotion.objects;

import com.example.projectilemotion.MainActivity;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

/**
 * This example demonstrates how to create a websocket connection to a server. Only the most
 * important callbacks are overloaded.
 */
public class ExampleClient extends WebSocketClient {

    private Gson gs = new Gson();
    public ArrayList<Point> resultList;
    public int isClosed = 0;
    private float angle;
    private float speed;

    public ExampleClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ExampleClient(URI serverURI, float speed, float angle) {
        super(serverURI);
        this.angle = angle;
        this.speed = speed;
    }

    public ExampleClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public ExampleClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        isClosed = 0;
        resultList = new ArrayList<>();
        send(String.valueOf(angle));
        send(String.valueOf(speed));
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        resultList.add(gs.fromJson(message, Point.class));
//        System.out.println(gs.fromJson(message, Point.class).toString());
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        isClosed = 1;
        for(Point p: resultList){
            System.out.println(p.toString());
        }
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }
}