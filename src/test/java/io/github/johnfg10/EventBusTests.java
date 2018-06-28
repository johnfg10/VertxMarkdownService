package io.github.johnfg10;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

public class EventBusTests extends MarkdownServiceTestBase {

    @Test
    public void testFormattedStringRaw(TestContext ctx) throws Exception{
        String formattedString = "a simple paragraph";
        Async async = ctx.async();
        JsonObject msg = new JsonObject();
        msg.put("markdown", formattedString);
        DeliveryOptions delOpts = new DeliveryOptions();
        delOpts.addHeader("action", "GenerateHtml");
        vertx.eventBus().send(MarkdownServiceVerticle.ADDRESS, msg, delOpts, reply -> {
            if (reply.failed()){
                ctx.fail(reply.cause());
            }
            ctx.assertNotNull(reply.result(), "Reply must not be null");
            ctx.assertTrue(reply.result().body() instanceof String, "A string was returned as expected");
            System.out.println(reply.result().body());
            System.out.println("<p>a simple paragraph</p>");
            ctx.assertEquals("<p>a simple paragraph</p>\n", reply.result().body());
            async.complete();
        });
    }

    @Test
    public void testFormattedStringProxy(TestContext ctx) throws Exception{
        String formattedString = "a simple paragraph";
        Async async = ctx.async();
        MarkdownServiceVertxEBProxy proxy = new MarkdownServiceVertxEBProxy(vertx, MarkdownServiceVerticle.ADDRESS);
        proxy.GenerateHtml(formattedString, res -> {
            ctx.assertTrue(res.succeeded());
            ctx.assertEquals("<p>a simple paragraph</p>\n", res.result());
            async.complete();
        });
    }

    @Test
    public void testComplexStringRaw(TestContext ctx){
        String formattedString = "# hello \n hello ~~test~~";
        Async async = ctx.async();
        JsonObject msg = new JsonObject();
        msg.put("markdown", formattedString);
        DeliveryOptions delOpts = new DeliveryOptions();
        delOpts.addHeader("action", "GenerateHtml");
        vertx.eventBus().send(MarkdownServiceVerticle.ADDRESS, msg, delOpts, reply -> {
            if (reply.failed()){
                ctx.fail(reply.cause());
            }
            ctx.assertNotNull(reply.result(), "Reply must not be null");
            ctx.assertTrue(reply.result().body() instanceof String, "A string was returned as expected");
            System.out.println(reply.result().body());
            ctx.assertEquals("<h1>hello</h1>\n" +
                    "<p>hello <del>test</del></p>\n", reply.result().body());
            async.complete();
        });
    }
}
