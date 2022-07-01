package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // Blend of using server and router.
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.route().handler(ctx -> {
      HttpServerResponse response = ctx.response();
      response.putHeader("content-type", "text/plain");
      response.end("Hello World from Vert.x-Web!");
    });

    server.listen(8000);   
  }
}
