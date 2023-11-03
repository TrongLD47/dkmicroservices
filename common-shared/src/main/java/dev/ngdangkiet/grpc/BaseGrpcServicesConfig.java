package dev.ngdangkiet.grpc;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ngdangkiet
 * @since 10/31/2023
 */

@Slf4j
public class BaseGrpcServicesConfig {

    protected final List<ManagedChannel> channels = new ArrayList<>();
    @Qualifier("eurekaClient")
    private final EurekaClient eurekaClient;

    public BaseGrpcServicesConfig(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    protected InstanceInfo getGrpcInstanceInfo(String serviceName) {
        // secure = false => non-secure-port (grpc server port)
        return eurekaClient.getNextServerFromEureka(serviceName, false);
    }

    protected ManagedChannel newChannel(String host, int port) {
        log.info("Bootstrapping GRPC proxy... Host=" + host + "; Port=" + port);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        channels.add(channel);
        log.info("GRPC proxy configured");
        return channel;
    }

    @PreDestroy
    public void proxyShutdown() throws InterruptedException {
        log.info("Shutdown of GRPC proxy in progress...");
        for (ManagedChannel channel : channels) {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
        log.info("GRPC proxy shutdown");
    }
}
