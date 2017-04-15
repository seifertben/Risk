package edu.brown.cs.jhbgbssg.Client;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;

@WebSocket
public class Matches {

  private static final Gson GSON = new Gson();
  private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();
  private Map<UUID, Match> playerToGames = Collections.synchronizedMap(new HashMap<>());
  private Map<UUID, Session> playerToSession = Collections.synchronizedMap(new HashMap<>());

  private static enum MESSAGE_TYPE {
    CONNECT,
    UPDATE,
    START
  }

  @OnWebSocketConnect
  public void connected(Session session) throws IOException {
    sessions.add(session);
    UUID playerId = UUID.randomUUID();
    JsonObject jj = new JsonObject();
    jj.addProperty("type", MESSAGE_TYPE.CONNECT.ordinal());
    System.out.println(playerId.toString());
    jj.addProperty("id", playerId.toString());
    playerToSession.put(playerId, session);
    session.getRemote().sendString(jj.toString());
  }

  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    sessions.remove(session);
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
	System.out.println("HI");
    JsonObject received = GSON.fromJson(message, JsonObject.class);
    System.out.println(received.get("type").getAsInt());
    JsonArray partners = received.get("partners").getAsJsonArray();
    for (JsonElement player : partners) {
      System.out.println(UUID.fromString(player.getAsString()));
    }
    JsonObject update = new JsonObject();
    session.getRemote().sendString(update.toString());
  }
}
