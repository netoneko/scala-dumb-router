package dumb.router

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import dumb.router.impl.{RealRequest,RealResponse}

class Servlet extends HttpServlet with Dsl {
  override def doGet(req: HttpServletRequest, res: HttpServletResponse) {
    println(req.getServletPath)
    getRequest(RealRequest(req), RealResponse(res))
  }

  override def doPost(req: HttpServletRequest, res: HttpServletResponse) {
    postRequest(RealRequest(req), RealResponse(res))
  }

  def getRequest(req: Request, res: Response) {
    res setStatus 200
    res setContentType "text/html"
    res setCharacterEncoding "UTF-8"

    router.getRoute(req, res)
  }

  def postRequest(req: Request, res: Response) {
    res setStatus 200
    res setContentType "text/html"
    res setCharacterEncoding "UTF-8"

    router.postRoute(req, res)
  }
}
