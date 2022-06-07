package com.elsevier.techassociates.springcrudapi;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class BaseIntegrationTestServer implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static DockerComposeContainer testContainer = new DockerComposeContainer(new File("./docker/docker-compose.yml"))
            .withExposedService("postgres", 5432, Wait.forLogMessage(".*database system is ready to accept connections.*", 1));

    private static boolean started = false;
    private static ConfigurableApplicationContext applicationContext = null;

    @Override
    public void beforeAll(ExtensionContext context) {
        testContainer.withLocalCompose(true);
        testContainer.start();

        if (!started) {
            started = true;
            logger.info("Starting server");

            applicationContext = SpringApplication.run(Application.class);

            final String closeHookKey = this.getClass().getSimpleName() + "-close-hook";
            context.getRoot().getStore(GLOBAL).put(closeHookKey, this);
        }
    }

    @Override
    public void close() {
        logger.info("Stopping server");

        applicationContext.close();
        testContainer.stop();
    }
}
