package dumb.router

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Servlet extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    response.setContentType("text/html")
    response.setCharacterEncoding("UTF-8")

    Router.route(request, response)
  }
}
