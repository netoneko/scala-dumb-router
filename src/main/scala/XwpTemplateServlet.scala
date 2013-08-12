package com.earldouglas.xwptemplate

import scala.xml.NodeSeq
import javax.servlet.http.HttpServlet

class XwpTemplateServlet extends HttpServlet {

  import javax.servlet.http.HttpServletRequest
  import javax.servlet.http.HttpServletResponse

  get("/", new Handler)
  get("/hello", new Handler)

  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {

    response.setContentType("text/html")
    response.setCharacterEncoding("UTF-8")

    Router.route(request, response)
  }

  class Handler {
    def handle(request: HttpServletRequest, response: HttpServletResponse) {
      val responseBody: NodeSeq = <html><body><h1>Hello, world!</h1></body></html>
      response.getWriter.write(responseBody.toString)
    }
  }

  class NotFoundHandler extends Handler {
    override def handle(request: HttpServletRequest, response: HttpServletResponse) {
      val responseBody: NodeSeq = <html><body><h1>404</h1></body></html>
      response.getWriter.write(responseBody.toString)
    }
  }

  object Router {
    val routes = scala.collection.mutable.Map[String,Handler]()

    def route(request: HttpServletRequest, response: HttpServletResponse) {
      println(request.getRequestURI())

      (routes get request.getRequestURI() match {
        case Some(handler) => handler
        case None => new NotFoundHandler()
      }).handle(request, response)
    }
  }

  def get(url: String, handler: Handler) = {
    Router.routes(url) = handler
  }
}
