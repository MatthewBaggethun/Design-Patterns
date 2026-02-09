package structural.decorator.httpPipeline.Test.FakeTestStrategies;

import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Strategy.Auth.AuthenticationStrategy;

public class FakeAuthenticationStrategy implements AuthenticationStrategy {

	private final String headerValue;

	public FakeAuthenticationStrategy(String headerValue) {
        this.headerValue = headerValue;
    }

	@Override
	public String headerName() {
		return "Authorization";
	}

	@Override
	public String headerValue(Request request) {
		return headerValue;
	}


}
