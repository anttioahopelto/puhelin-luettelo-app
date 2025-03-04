package service

import org.scalatra._

class WebService extends ScalatraServlet {
  // Enable CORS headers for all responses
  before() {
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
  }

  get("/") {
    "Get-testi"
  }
}
