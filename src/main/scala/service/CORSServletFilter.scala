package service

import javax.servlet._
import javax.servlet.http._

class CORSServletFilter extends Filter {
  override def doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain): Unit = {
    val httpResponse = res.asInstanceOf[HttpServletResponse]
    httpResponse.setHeader("Access-Control-Allow-Origin", "*")
    httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
    httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
    httpResponse.setHeader("Access-Control-Allow-Credentials", "true")

    if (req.asInstanceOf[HttpServletRequest].getMethod != "OPTIONS") {
      chain.doFilter(req, res)
    }
  }

  override def init(filterConfig: FilterConfig): Unit = {}
  override def destroy(): Unit = {}
}
