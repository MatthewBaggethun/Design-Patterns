package structural.decorator.httpPipeline.httpPipelineClient;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import structural.decorator.httpPipeline.Factory.PipelineConfig;
import structural.decorator.httpPipeline.Factory.PipelineFactory;
import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.Model.HttpMethod;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;
import structural.decorator.httpPipeline.Strategy.Auth.BearerTokenStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.ExponentialBackoffStrategy;

public class Main {

	public static void main(String[] args) {

		syncExample();

		asyncExample();

	}

	private static void syncExample() {
		System.out.println("=== SYNCHRONOUS REQUEST ===");

		PipelineConfig config = new PipelineConfig.Builder().enableLogging(true).enableRetry(3)
				.withBackoffStrategy(new ExponentialBackoffStrategy(Duration.ofMillis(200), 2.0, Duration.ofSeconds(2)))
				.enableTimeout(Duration.ofSeconds(5)).authStrategy(new BearerTokenStrategy("demo-token")).build();

		HttpHandler pipeline = PipelineFactory.createPipeline(config);

		Request request = Request.of(HttpMethod.GET, URI.create("https://httpbin.org/get"));

		try {
			Response response = pipeline.execute(request);
			System.out.println("Sync status: " + response.getStatusCode() + "\n");
			System.out.println("=== RESPONSE BODY ===" + "\n" + response.getBody() + "\n=====================");
		} catch (Exception e) {
			System.err.println("Request failed after retries:");
			e.printStackTrace();
		}

	}

	private static void asyncExample() {
		System.out.println("\n=== ASYNCHRONOUS REQUEST ===");

		PipelineConfig config = new PipelineConfig.Builder().enableLogging(true).enableRetry(2)
				.enableTimeout(Duration.ofSeconds(3)).build();

		HttpHandler pipeline = PipelineFactory.createPipeline(config);

		Request request = Request.of(HttpMethod.GET, URI.create("https://httpbin.org/get"));

		CompletableFuture<Response> future = pipeline.executeAsync(request);

		future.thenAccept(response -> System.out.println("Async status: " + response.getStatusCode() + "\n"
				+ "=== RESPONSE BODY ===" + "\n" + response.getBody() + "\n====================="))
				.exceptionally(ex -> {
					System.err.println("Async request failed:");
					ex.printStackTrace();
					return null;
				}).join();

	}

}
