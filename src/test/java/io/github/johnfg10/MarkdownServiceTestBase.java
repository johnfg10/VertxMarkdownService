package io.github.johnfg10;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class MarkdownServiceTestBase {

    protected Vertx vertx;

    @Before
    public void before(TestContext testCtx){
        vertx = Vertx.vertx();
        vertx.deployVerticle("io.github.johnfg10.MarkdownServiceVerticle", testCtx.asyncAssertSuccess());
    }

    @After
    public void after(TestContext testCtx){
        if (vertx != null){
            vertx.close(testCtx.asyncAssertSuccess());
        }
    }
}
