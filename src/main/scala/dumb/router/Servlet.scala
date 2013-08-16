package dumb.router

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import dumb.router.Router.route

class Servlet extends HttpServlet {
  override def doGet(req: HttpServletRequest, res: HttpServletResponse) {
    res setStatus 200
    res setContentType "text/html"
    res setCharacterEncoding "UTF-8"

    route (Request(req), Response(res))
  }
}
