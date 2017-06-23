package com.mcs.calc.controller;

import com.mcs.calc.common.CalcApplicationException;
import com.mcs.calc.facade.CalculatorFacade;
import com.mcs.calc.facade.impl.CalculatorFacadeImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class CalculatorWebControllerTest {

  public static final Logger LOGGER = LoggerFactory.getLogger(CalculatorWebControllerTest.class);

  @Spy
  public static CalculatorFacade calculatorFacade = CalculatorFacadeImpl.getInstance();


  @BeforeClass
  public static void beforeClass() {
    CalculatorWebController.main(null);
  }
  @AfterClass
  public static void afterClass() {
    Spark.stop();
  }

  @Before
  public void setUp() throws Exception {
    CalculatorWebController.setCalculatorFacade(calculatorFacade);
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void addRoute() {
    TestResponse res = request("GET", "/add?num1=1&num2=2");
    verify(calculatorFacade).addition(new BigDecimal(1), new BigDecimal(2));
    assertEquals(200, res.status);
    assertEquals("3", res.body);
  }

  @Test
  public void divRoute() throws CalcApplicationException {
    TestResponse res = request("GET", "/div?num1=1&num2=2");
    verify(calculatorFacade).division(new BigDecimal(1), new BigDecimal(2));
    assertEquals(200, res.status);
    assertEquals("0.50", res.body);
  }

  @Test(expected = Exception.class)
  public void divRouteException() throws CalcApplicationException {
    TestResponse res = request("GET", "/div?num1=1&num2=0");
    verify(calculatorFacade).division(new BigDecimal(1), new BigDecimal(2));
  }

  private TestResponse request(String method, String path) {
    try {
      URL url = new URL("http://127.0.0.1:4567" + path);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(method);
      connection.setDoOutput(true);
      connection.connect();
      String body = IOUtils.toString(connection.getInputStream());
      return new TestResponse(connection.getResponseCode(), body);
    } catch (IOException e) {
      LOGGER.error("ERROR OCCURRED DURING REQUEST", e);
      //fail("Sending request failed: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  private static class TestResponse {

    public final String body;
    public final int status;

    public TestResponse(int status, String body) {
      this.status = status;
      this.body = body;
    }
  }
}