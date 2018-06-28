package io.github.johnfg10.impl;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import io.github.johnfg10.MarkdownService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.Arrays;

public class FlexmarkMarkdownServiceImpl implements MarkdownService {

    private Parser parser;
    private HtmlRenderer renderer;

    public FlexmarkMarkdownServiceImpl(JsonObject config){
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    @Override
    public void GenerateHtml(String markdown, Handler<AsyncResult<String>> handler) {
        Node doc = parser.parse(markdown);
        handler.handle(Future.succeededFuture(renderer.render(doc)));
    }
}
