package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // Blend of using server and router.
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);
    Route route = router.route("/some/path");

    route.handler(ctx -> {
      HttpServerResponse response = ctx.response();
      response.setChunked(true);
      response.write("route1\n");
      ctx.vertx().setTimer(5000, tid -> ctx.next());
    });

    route.handler(ctx -> {
      HttpServerResponse response = ctx.response();
      response.write("route2\n");
      ctx.vertx().setTimer(5000, tid -> ctx.next());
    });

    route.handler(ctx -> {
      HttpServerResponse response = ctx.response();
      response.write("route3\n");
      ctx.response().end();
    });

    server.requestHandler(router).listen(8000);
   
  }
}
