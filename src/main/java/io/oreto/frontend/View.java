package io.oreto.frontend;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class View extends AbstractVerticle {
    public static int getPort() {
        String PORT = System.getenv("PORT");
        return PORT == null ? 8080 : Integer.parseInt(System.getenv("PORT"));
    }

    @Override
    public final void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/assets/*").handler(StaticHandler.create("assets"));
        router.get("/").handler(ctx -> {
            ctx.response().putHeader("content-type", "text/html").end(html);
        });
        server.requestHandler(router);
        server.listen(getPort(), http -> {
           if (http.succeeded()) {
               System.out.printf("HTTP server started on port %s", http.result().actualPort());
           }
        });
    }

    static final String html = """
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hello</title>
    <link href="/assets/app.css" rel="stylesheet">
    <script src="/assets/app.js" type="module"></script>
  </head>
  <body>
  <section class="section">
    <div class="container">
      <h1 class="title">
        Hello World
      </h1>
      <p class="subtitle">
        <strong>Hello</strong>!
      </p>
    </div>
  </section>
  </body>
</html>
""";
}
