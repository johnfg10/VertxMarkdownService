package io.github.johnfg10.impl;

import io.github.johnfg10.MarkdownService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class CommonMarkMarkdownServiceImpl implements MarkdownService {

    private Parser parser;
    private HtmlRenderer renderer;

    public CommonMarkMarkdownServiceImpl(JsonObject config){
        parser = Parser.builder().build();
        renderer = HtmlRenderer.builder().build();
    }

    @Override
    public void GenerateHtml(String markdown, Handler<AsyncResult<String>> handler) {
        Node doc = parser.parse(markdown);
        handler.handle(Future.succeededFuture(renderer.render(doc)));
    }
}
