package structural.decorator.httpPipeline.Test.TestHandler;

import java.util.Objects;
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
public class TestHandler implements HttpHandler {

	private final Supplier<Response> responseSupplier;
	private final AtomicInteger callCount = new AtomicInteger();
	private volatile Request lastRequest;

	/**
	 * Constructs a TestHandler with the given response supplier.
	 *
	 * @param responseSupplier A supplier that generates the response to be returned
	 *                         for each request. Must not be null.
	 */
	public TestHandler(Supplier<Response> responseSupplier) {
		this.responseSupplier = Objects.requireNonNull(responseSupplier);
	}

	@Override
	public Response execute(Request request) {
		recordRequest(request);
		return responseSupplier.get();
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		recordRequest(request);

		try {
			return CompletableFuture.completedFuture(responseSupplier.get());
		} catch (Throwable t) {
			return CompletableFuture.failedFuture(t);
		}
	}

	private void recordRequest(Request request) {
		callCount.incrementAndGet();
		lastRequest = request;
	}

	public int callCount() {
		return callCount.get();
	}

	public Request lastRequest() {
		return lastRequest;
	}

}
