package dumb.router

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object Router {
  val routes = scala.collection.mutable.Map[String, Handler]() //{def handle(request: HttpServletRequest,response: HttpServletResponse):Any}]()

  def route(request: HttpServletRequest, response: HttpServletResponse) {
    println(request.getRequestURI())

    (routes get request.getRequestURI() match {
      case Some(handler) => handler
      case None => new NotFoundHandler()
    }).handle(request, response)
  }
}
