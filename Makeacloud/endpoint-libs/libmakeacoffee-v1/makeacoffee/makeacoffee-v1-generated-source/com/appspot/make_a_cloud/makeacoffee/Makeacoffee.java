/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-11-17 18:43:33 UTC)
 * on 2014-11-27 at 10:30:02 UTC 
 * Modify at your own risk.
 */

package com.appspot.make_a_cloud.makeacoffee;

/**
 * Service definition for Makeacoffee (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link MakeacoffeeRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Makeacoffee extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.18.0-rc of the makeacoffee library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://make-a-cloud.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "makeacoffee/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Makeacoffee(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Makeacoffee(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * An accessor for creating requests from the Devices collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Makeacoffee makeacoffee = new Makeacoffee(...);}
   *   {@code Makeacoffee.Devices.List request = makeacoffee.devices().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public Devices devices() {
    return new Devices();
  }

  /**
   * The "devices" collection of methods.
   */
  public class Devices {

    /**
     * Create a request for the method "devices.fire".
     *
     * This request holds the parameters needed by the makeacoffee server.  After setting any optional
     * parameters, call the {@link Fire#execute()} method to invoke the remote operation.
     *
     * @param device
     * @param event
     * @return the request
     */
    public Fire fire(java.lang.String device, java.lang.String event) throws java.io.IOException {
      Fire result = new Fire(device, event);
      initialize(result);
      return result;
    }

    public class Fire extends MakeacoffeeRequest<com.appspot.make_a_cloud.makeacoffee.model.State> {

      private static final String REST_PATH = "devices/{device}/events/{event}";

      /**
       * Create a request for the method "devices.fire".
       *
       * This request holds the parameters needed by the the makeacoffee server.  After setting any
       * optional parameters, call the {@link Fire#execute()} method to invoke the remote operation. <p>
       * {@link Fire#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param device
       * @param event
       * @since 1.13
       */
      protected Fire(java.lang.String device, java.lang.String event) {
        super(Makeacoffee.this, "POST", REST_PATH, null, com.appspot.make_a_cloud.makeacoffee.model.State.class);
        this.device = com.google.api.client.util.Preconditions.checkNotNull(device, "Required parameter device must be specified.");
        this.event = com.google.api.client.util.Preconditions.checkNotNull(event, "Required parameter event must be specified.");
      }

      @Override
      public Fire setAlt(java.lang.String alt) {
        return (Fire) super.setAlt(alt);
      }

      @Override
      public Fire setFields(java.lang.String fields) {
        return (Fire) super.setFields(fields);
      }

      @Override
      public Fire setKey(java.lang.String key) {
        return (Fire) super.setKey(key);
      }

      @Override
      public Fire setOauthToken(java.lang.String oauthToken) {
        return (Fire) super.setOauthToken(oauthToken);
      }

      @Override
      public Fire setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Fire) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Fire setQuotaUser(java.lang.String quotaUser) {
        return (Fire) super.setQuotaUser(quotaUser);
      }

      @Override
      public Fire setUserIp(java.lang.String userIp) {
        return (Fire) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.String device;

      /**

       */
      public java.lang.String getDevice() {
        return device;
      }

      public Fire setDevice(java.lang.String device) {
        this.device = device;
        return this;
      }

      @com.google.api.client.util.Key
      private java.lang.String event;

      /**

       */
      public java.lang.String getEvent() {
        return event;
      }

      public Fire setEvent(java.lang.String event) {
        this.event = event;
        return this;
      }

      @Override
      public Fire set(String parameterName, Object value) {
        return (Fire) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "devices.get".
     *
     * This request holds the parameters needed by the makeacoffee server.  After setting any optional
     * parameters, call the {@link Get#execute()} method to invoke the remote operation.
     *
     * @param id
     * @return the request
     */
    public Get get(java.lang.String id) throws java.io.IOException {
      Get result = new Get(id);
      initialize(result);
      return result;
    }

    public class Get extends MakeacoffeeRequest<com.appspot.make_a_cloud.makeacoffee.model.Device> {

      private static final String REST_PATH = "devices/{id}";

      /**
       * Create a request for the method "devices.get".
       *
       * This request holds the parameters needed by the the makeacoffee server.  After setting any
       * optional parameters, call the {@link Get#execute()} method to invoke the remote operation. <p>
       * {@link Get#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param id
       * @since 1.13
       */
      protected Get(java.lang.String id) {
        super(Makeacoffee.this, "GET", REST_PATH, null, com.appspot.make_a_cloud.makeacoffee.model.Device.class);
        this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public Get setAlt(java.lang.String alt) {
        return (Get) super.setAlt(alt);
      }

      @Override
      public Get setFields(java.lang.String fields) {
        return (Get) super.setFields(fields);
      }

      @Override
      public Get setKey(java.lang.String key) {
        return (Get) super.setKey(key);
      }

      @Override
      public Get setOauthToken(java.lang.String oauthToken) {
        return (Get) super.setOauthToken(oauthToken);
      }

      @Override
      public Get setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Get) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Get setQuotaUser(java.lang.String quotaUser) {
        return (Get) super.setQuotaUser(quotaUser);
      }

      @Override
      public Get setUserIp(java.lang.String userIp) {
        return (Get) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.String id;

      /**

       */
      public java.lang.String getId() {
        return id;
      }

      public Get setId(java.lang.String id) {
        this.id = id;
        return this;
      }

      @Override
      public Get set(String parameterName, Object value) {
        return (Get) super.set(parameterName, value);
      }
    }

  }

  /**
   * An accessor for creating requests from the Houses collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Makeacoffee makeacoffee = new Makeacoffee(...);}
   *   {@code Makeacoffee.Houses.List request = makeacoffee.houses().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public Houses houses() {
    return new Houses();
  }

  /**
   * The "houses" collection of methods.
   */
  public class Houses {

    /**
     * Create a request for the method "houses.get".
     *
     * This request holds the parameters needed by the makeacoffee server.  After setting any optional
     * parameters, call the {@link Get#execute()} method to invoke the remote operation.
     *
     * @param id
     * @return the request
     */
    public Get get(java.lang.String id) throws java.io.IOException {
      Get result = new Get(id);
      initialize(result);
      return result;
    }

    public class Get extends MakeacoffeeRequest<com.appspot.make_a_cloud.makeacoffee.model.House> {

      private static final String REST_PATH = "houses/{id}";

      /**
       * Create a request for the method "houses.get".
       *
       * This request holds the parameters needed by the the makeacoffee server.  After setting any
       * optional parameters, call the {@link Get#execute()} method to invoke the remote operation. <p>
       * {@link Get#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param id
       * @since 1.13
       */
      protected Get(java.lang.String id) {
        super(Makeacoffee.this, "GET", REST_PATH, null, com.appspot.make_a_cloud.makeacoffee.model.House.class);
        this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public Get setAlt(java.lang.String alt) {
        return (Get) super.setAlt(alt);
      }

      @Override
      public Get setFields(java.lang.String fields) {
        return (Get) super.setFields(fields);
      }

      @Override
      public Get setKey(java.lang.String key) {
        return (Get) super.setKey(key);
      }

      @Override
      public Get setOauthToken(java.lang.String oauthToken) {
        return (Get) super.setOauthToken(oauthToken);
      }

      @Override
      public Get setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Get) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Get setQuotaUser(java.lang.String quotaUser) {
        return (Get) super.setQuotaUser(quotaUser);
      }

      @Override
      public Get setUserIp(java.lang.String userIp) {
        return (Get) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.String id;

      /**

       */
      public java.lang.String getId() {
        return id;
      }

      public Get setId(java.lang.String id) {
        this.id = id;
        return this;
      }

      @Override
      public Get set(String parameterName, Object value) {
        return (Get) super.set(parameterName, value);
      }
    }

  }

  /**
   * An accessor for creating requests from the Users collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Makeacoffee makeacoffee = new Makeacoffee(...);}
   *   {@code Makeacoffee.Users.List request = makeacoffee.users().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public Users users() {
    return new Users();
  }

  /**
   * The "users" collection of methods.
   */
  public class Users {

    /**
     * Create a request for the method "users.get".
     *
     * This request holds the parameters needed by the makeacoffee server.  After setting any optional
     * parameters, call the {@link Get#execute()} method to invoke the remote operation.
     *
     * @param id
     * @return the request
     */
    public Get get(java.lang.Long id) throws java.io.IOException {
      Get result = new Get(id);
      initialize(result);
      return result;
    }

    public class Get extends MakeacoffeeRequest<com.appspot.make_a_cloud.makeacoffee.model.User> {

      private static final String REST_PATH = "users/{id}";

      /**
       * Create a request for the method "users.get".
       *
       * This request holds the parameters needed by the the makeacoffee server.  After setting any
       * optional parameters, call the {@link Get#execute()} method to invoke the remote operation. <p>
       * {@link Get#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param id
       * @since 1.13
       */
      protected Get(java.lang.Long id) {
        super(Makeacoffee.this, "GET", REST_PATH, null, com.appspot.make_a_cloud.makeacoffee.model.User.class);
        this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public Get setAlt(java.lang.String alt) {
        return (Get) super.setAlt(alt);
      }

      @Override
      public Get setFields(java.lang.String fields) {
        return (Get) super.setFields(fields);
      }

      @Override
      public Get setKey(java.lang.String key) {
        return (Get) super.setKey(key);
      }

      @Override
      public Get setOauthToken(java.lang.String oauthToken) {
        return (Get) super.setOauthToken(oauthToken);
      }

      @Override
      public Get setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Get) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Get setQuotaUser(java.lang.String quotaUser) {
        return (Get) super.setQuotaUser(quotaUser);
      }

      @Override
      public Get setUserIp(java.lang.String userIp) {
        return (Get) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.Long id;

      /**

       */
      public java.lang.Long getId() {
        return id;
      }

      public Get setId(java.lang.Long id) {
        this.id = id;
        return this;
      }

      @Override
      public Get set(String parameterName, Object value) {
        return (Get) super.set(parameterName, value);
      }
    }

  }

  /**
   * Create a request for the method "login".
   *
   * This request holds the parameters needed by the makeacoffee server.  After setting any optional
   * parameters, call the {@link Login#execute()} method to invoke the remote operation.
   *
   * @param email
   * @return the request
   */
  public Login login(java.lang.String email) throws java.io.IOException {
    Login result = new Login(email);
    initialize(result);
    return result;
  }

  public class Login extends MakeacoffeeRequest<com.appspot.make_a_cloud.makeacoffee.model.User> {

    private static final String REST_PATH = "login/{email}";

    /**
     * Create a request for the method "login".
     *
     * This request holds the parameters needed by the the makeacoffee server.  After setting any
     * optional parameters, call the {@link Login#execute()} method to invoke the remote operation.
     * <p> {@link
     * Login#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param email
     * @since 1.13
     */
    protected Login(java.lang.String email) {
      super(Makeacoffee.this, "GET", REST_PATH, null, com.appspot.make_a_cloud.makeacoffee.model.User.class);
      this.email = com.google.api.client.util.Preconditions.checkNotNull(email, "Required parameter email must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public Login setAlt(java.lang.String alt) {
      return (Login) super.setAlt(alt);
    }

    @Override
    public Login setFields(java.lang.String fields) {
      return (Login) super.setFields(fields);
    }

    @Override
    public Login setKey(java.lang.String key) {
      return (Login) super.setKey(key);
    }

    @Override
    public Login setOauthToken(java.lang.String oauthToken) {
      return (Login) super.setOauthToken(oauthToken);
    }

    @Override
    public Login setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (Login) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public Login setQuotaUser(java.lang.String quotaUser) {
      return (Login) super.setQuotaUser(quotaUser);
    }

    @Override
    public Login setUserIp(java.lang.String userIp) {
      return (Login) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String email;

    /**

     */
    public java.lang.String getEmail() {
      return email;
    }

    public Login setEmail(java.lang.String email) {
      this.email = email;
      return this;
    }

    @Override
    public Login set(String parameterName, Object value) {
      return (Login) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Makeacoffee}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Makeacoffee}. */
    @Override
    public Makeacoffee build() {
      return new Makeacoffee(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link MakeacoffeeRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setMakeacoffeeRequestInitializer(
        MakeacoffeeRequestInitializer makeacoffeeRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(makeacoffeeRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
