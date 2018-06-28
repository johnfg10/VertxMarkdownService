package io.github.johnfg10;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface MarkdownService {
    static MarkdownService create(JsonObject config){
        return new io.github.johnfg10.impl.FlexmarkMarkdownServiceImpl(config);
    }

    static MarkdownService createProxy(Vertx vertx, String address) {
        return new MarkdownServiceVertxEBProxy(vertx, address);
    }

    public void GenerateHtml(String markdown, Handler<AsyncResult<String>> handler);
}
