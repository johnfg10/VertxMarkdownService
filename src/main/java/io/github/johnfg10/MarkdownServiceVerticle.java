package io.github.johnfg10;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;

public class MarkdownServiceVerticle extends AbstractVerticle {
    public final static String ADDRESS = "io.github.johnfg10.markdown";
    private MarkdownService service;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        service = MarkdownService.create(config());
        String address = config().getString("address", ADDRESS);
        //service = new ServiceProxyBuilder(vertx).setAddress(address).build(MarkdownService.class);
        new ServiceBinder(vertx).setAddress(address).register(MarkdownService.class, service);
        startFuture.complete();
    }
}
