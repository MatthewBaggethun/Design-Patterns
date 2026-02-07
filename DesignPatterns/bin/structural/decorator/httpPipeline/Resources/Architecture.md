http-client-pipeline/
├── README.md
├── build.gradle (or pom.xml)
├── settings.gradle
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── yourname/
│   │               └── httpclient/
│   │
│   │                   ├── core/
│   │                   │   ├── HttpHandler.java
│   │                   │   ├── HttpHandlerDecorator.java
│   │                   │   └── exceptions/
│   │                   │       ├── HttpException.java
│   │                   │       ├── RetryExhaustedException.java
│   │                   │       └── TimeoutException.java
│   │
│   │                   ├── model/
│   │                   │   ├── Request.java
│   │                   │   ├── Response.java
│   │                   │   ├── HttpMethod.java
│   │                   │   └── Headers.java
│   │
│   │                   ├── execution/
│   │                   │   ├── JavaHttpHandler.java
│   │                   │   ├── HttpRequestMapper.java
│   │                   │   └── HttpResponseMapper.java
│   │
│   │                   ├── decorators/
│   │                   │   ├── LoggingDecorator.java
│   │                   │   ├── RetryDecorator.java
│   │                   │   ├── TimeoutDecorator.java
│   │                   │   ├── AuthenticationDecorator.java
│   │                   │   ├── CachingDecorator.java
│   │                   │   └── CircuitBreakerDecorator.java
│   │
│   │                   ├── strategy/
│   │                   │   ├── retry/
│   │                   │   │   ├── RetryPolicy.java
│   │                   │   │   ├── FixedDelayPolicy.java
│   │                   │   │   └── ExponentialBackoffPolicy.java
│   │                   │   │
│   │                   │   ├── auth/
│   │                   │   │   ├── AuthStrategy.java
│   │                   │   │   ├── ApiKeyAuth.java
│   │                   │   │   └── BearerTokenAuth.java
│   │                   │   │
│   │                   │   └── cache/
│   │                   │       ├── Cache.java
│   │                   │       └── InMemoryCache.java
│   │
│   │                   ├── factory/
│   │                   │   ├── HttpPipelineFactory.java
│   │                   │   └── PipelineConfig.java
│   │
│   │                   ├── observer/         
│   │                   │   ├── RequestEvent.java
│   │                   │   └── RequestListener.java
│   │
│   │                   └── util/
│   │                       ├── TimeUtils.java
│   │                       └── RetryUtils.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── yourname/
│                   └── httpclient/
│
│                       ├── decorators/
│                       │   ├── LoggingDecoratorTest.java
│                       │   ├── RetryDecoratorTest.java
│                       │   └── TimeoutDecoratorTest.java
│                       │
│                       ├── execution/
│                       │   └── JavaHttpHandlerTest.java
│                       │
│                       ├── integration/
│                       │   ├── SyncPipelineIT.java
│                       │   └── AsyncPipelineIT.java
│                       │
│                       └── testutil/
│                           ├── FakeHttpHandler.java
│                           └── StubRetryPolicy.java
│
└── resources/
    ├── architecture.md
    ├── decorator-ordering.md
    └── sync-vs-async.md