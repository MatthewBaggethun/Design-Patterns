package structural.decorator.httpPipeline.Test.FakeHandler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;

/**
 * A simple HttpHandler implementation for testing purposes. It allows you to
 * specify a response supplier that generates the response to be returned for
 * each request. It also keeps track of the number of calls and the last request
 * received, which can be useful for assertions in tests.
 */
public class FakeHandler implements HttpHandler {

	private final Supplier<Response> behavior;
	private final AtomicInteger callCount = new AtomicInteger();
	private Request lastRequest;

	public FakeHandler(Supplier<Response> behavior) {
		this.behavior = behavior;
	}

	@Override
	public Response execute(Request request) {
		callCount.incrementAndGet();
		this.lastRequest = request;
		return behavior.get();
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		callCount.incrementAndGet();
		this.lastRequest = request;
		return CompletableFuture.supplyAsync(behavior);
	}

	public Request lastRequest() {
		return lastRequest;
	}

	public int callCount() {
		return callCount.get();
	}
}
