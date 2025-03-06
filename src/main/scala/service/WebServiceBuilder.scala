package service

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{DefaultServlet, ServletHolder}
import org.eclipse.jetty.webapp.WebAppContext

import javax.servlet.Servlet

object WebServiceBuilder {
  def buildWebService(port: Integer, webServiceClass: Class[_ <: Servlet]): Server = {
    val server = new Server(port)

    val context = new WebAppContext()
    context.setContextPath("/")
    // Set location where to serve static UI files
    context.setResourceBase("/app/UI")
    context.addServlet(webServiceClass, "/api/*")

    // Add the default servlet to serve static files
    val holder = new ServletHolder("default", classOf[DefaultServlet])
    holder.setInitParameter("dirAllowed", "true")
    context.addServlet(holder, "/")

    server.setHandler(context)
    server
  }
}
